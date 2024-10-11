package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.*
import nav_cons_pen_psak_samhandler.no.nav.inf.PSAKSamhandler
import nav_lib_cons_pen_psakpselv.no.nav.lib.pen.psakpselv.asbo.samhandler.ASBOPenFinnSamhandlerRequest
import no.nav.elsam.tpsamordningregistrering.v0_5.*
import no.nav.elsam.tpsamordningregistrering.v1_0.SlettTPYtelseFaultGeneriskMsg
import no.nav.elsam.tpsamordningregistrering.v1_0.SlettTPYtelseFaultTPYtelseIkkeFunnetMsg
import no.nav.pensjon.elsam.minibuss.misc.ServiceBusinessException
import no.nav.pensjon.elsam.minibuss.misc.toXMLGregorianCalendar
import no.nav.pensjon.elsam.minibuss.sam.SamService
import org.springframework.core.NestedExceptionUtils.getMostSpecificCause
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException.BadRequest
import org.springframework.web.client.HttpClientErrorException.NotFound
import java.util.Date

@Component
class NavConsElsamTplibTpSamordningRegistrering(
    private val samhandlerPartner: PSAKSamhandler,
    private val tpSamordningRegistreringIntPartner: TPSamordningRegistreringIntTOSamordning,
    private val samService: SamService,
) {
    @Throws(
        SlettTPYtelseFaultGeneriskMsg::class,
        SlettTPYtelseFaultTPYtelseIkkeFunnetMsg::class
    )
    fun slettTPYtelse(slettTPYtelseReq: SlettTPYtelseReq) =
        try {
            samService.slettTPYtelse(slettTPYtelseReq)
        } catch (e: BadRequest) {
            val melding = e.message
            throw SlettTPYtelseFaultGeneriskMsg(
                melding,
                FaultGenerisk().apply {
                    errorCode = "InternalError"
                    errorDescription = melding
                    errorDetails.add("Feil i input-data")
                }
            )
        } catch (e: NotFound) {
            if (e.message?.contains("No matching ytelse found for tssEksternId") == true) {
                val melding = "Error Id: 0, Error Message: " +
                        e.message!!.substring(e.message!!.lastIndexOf("No matching ytelse found for tssEksternId"), e.message!!.length -1)
                throw SlettTPYtelseFaultTPYtelseIkkeFunnetMsg(
                    melding,
                    FaultTPYtelseIkkeFunnet().apply {
                        errorMessage = melding
                        errorSource = "MODULE: nav-ent-sto-sam / COMPONENT: SamordningTOSamordningProcessProviderRemote / IF(OP): Samordning(opprettTPSamordning) / REF: SamordningProcessProviderRemotePartner IF(OP): SamordningProcessProviderRemote(samordneVedtak)"
                        dateTimeStamp = Date().toXMLGregorianCalendar()
                    }
                )
            } else {
                throwSlettTPYtelseFaultGeneriskMsg(e)
            }
        } catch (e: Exception) {
            throwSlettTPYtelseFaultGeneriskMsg(e)
        }

    private fun throwSlettTPYtelseFaultGeneriskMsg(e: Exception)  {
        val melding = e.message
        throw SlettTPYtelseFaultGeneriskMsg(
            melding,
            FaultGenerisk().also {
                it.errorCode = "InternalError"
                it.errorDescription = melding
                it.errorDetails.add(getMostSpecificCause(e).toString())
            }
        )
    }

    @Throws(
        ServiceBusinessException::class,
        OpprettRefusjonskravIntFaultSamordningsIdOgPersonKorrelererIkkeMsg::class,
        OpprettRefusjonskravIntFaultAlleredeMottattRefusjonskravMsg::class,
        OpprettRefusjonskravIntFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg::class,
        OpprettRefusjonskravIntFaultSamordningsIdIkkeFunnetMsg::class,
        OpprettRefusjonskravIntFaultRefusjonskravUtenforTidsfristMsg::class,
        OpprettRefusjonskravIntFaultGeneriskMsg::class
    )
    fun opprettRefusjonskrav(opprettRefusjonskravReq: OpprettRefusjonskravReq) =
        try {
            tpSamordningRegistreringIntPartner.opprettRefusjonskravInt(
                OpprettRefusjonskravReqInt().apply {
                    extRequest = opprettRefusjonskravReq
                    tssEksternId = mapTPnrToTSSEksternId(opprettRefusjonskravReq.tpnr)
                }
            )
        } catch (e: RuntimeException) {
            throw createTechnicalFault(e.message, getMostSpecificCause(e).toString())
        }

    /**
     * Map from TP-nummer to tssEksternId by searching TSS for samhandler with
     * samhandlerType TEPE, idType TPNR and the specified TP-nummer Should only
     * return one tssEksternId
     *
     * @param tpNr TP-number (4 digits)
     *
     * @return eksternTSSId (key to Samhandler)
     */
    @Throws(ServiceBusinessException::class)
    private fun mapTPnrToTSSEksternId(tpNr: String): String {
        // Build request object for samhandler
        val samhandlerResponse = try {
            samhandlerPartner.finnSamhandler(ASBOPenFinnSamhandlerRequest().apply {
                offentligId = tpNr
                idType = "TPNR"
                samhandlerType = "TEPE"
            })
        } catch (e: RuntimeException) {
            throw createTechnicalFault(
                e.message, getMostSpecificCause(e).toString()
            )
        }

        if (samhandlerResponse == null) {
            throw createTechnicalFault(
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "Tomt svar ved oppslag av samhandler"
            )
        }

        val samhandlere = samhandlerResponse.samhandlere
        if (samhandlere.size > 1) {
            // Got more than 1 samhandler in the response. Should receive only 1
            throw createTechnicalFault(
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "Ikke entydig treff på TP-nr"
            )
        } else if (samhandlere.size == 1) {
            return (
                    samhandlere[0].avdelinger
                        ?: throw createTechnicalFault(
                            "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                            "Fant ingen avdelinger på samhandleren"
                        )
                    )
                .firstOrNull { it.avdelingsnr == "01" }
                ?.idTSSEkstern
                ?: throw createTechnicalFault(
                    "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                    "Ikke registrert en avdeling 01 på samhandleren"
                )
        } else {
            throw createTechnicalFault(
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "TP-nummeret er ikke registrert"
            )
        }
    }

    private fun createTechnicalFault(errorDescription: String?, errorDetail: String) =
        createTechnicalFault(errorDescription, listOf(errorDetail))

    private fun createTechnicalFault(errorDescription: String?, errorDetails: List<String>) =
        ServiceBusinessException(
            FaultGenerisk().also {
                it.errorCode = "InternalError"
                it.errorDescription = errorDescription
                it.errorDetails.addAll(errorDetails)
            }
        )
}
