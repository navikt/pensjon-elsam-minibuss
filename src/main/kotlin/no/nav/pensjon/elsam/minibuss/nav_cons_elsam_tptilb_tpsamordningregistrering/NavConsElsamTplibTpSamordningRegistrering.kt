package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import no.nav.elsam.tpsamordningregistrering.v0_5.*
import no.nav.elsam.tpsamordningregistrering.v1_0.OpprettRefusjonskravFaultAlleredeMottattRefusjonskravMsg
import no.nav.elsam.tpsamordningregistrering.v1_0.OpprettRefusjonskravFaultGeneriskMsg
import no.nav.elsam.tpsamordningregistrering.v1_0.OpprettRefusjonskravFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg
import no.nav.elsam.tpsamordningregistrering.v1_0.OpprettRefusjonskravFaultSamordningsIdIkkeFunnetMsg
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
    )
    fun opprettRefusjonskrav(request: OpprettRefusjonskravReq) {
        try {
            val response = samService.opprettRefusjonskrav(
                request.fnr,
                request.tpnr,
                request.samordningsmeldingId.toLong(),
                request.refusjonskrav,
                request.periodisertBelopListe
            )
            when (response.exceptionType) {
                SamService.OpprettRefusjonskravExceptions.ALLEREDE_REGISTRERT_ELLER_UTENFOR_FRIST -> {
                    val melding = "Error Id: 0, Error Message: " + response.message
                    throw OpprettRefusjonskravFaultAlleredeMottattRefusjonskravMsg(
                        melding,
                        FaultAlleredeMottattRefusjonskrav().apply {
                            errorMessage = melding
                            errorSource =
                                "MODULE: nav-ent-sto-sam / COMPONENT: SamordningTOSamordningProcessProviderRemote / IF(OP): Samordning(opprettTPSamordning) / REF: SamordningProcessProviderRemotePartner IF(OP): SamordningProcessProviderRemote(samordneVedtak)"
                            dateTimeStamp = Date().toXMLGregorianCalendar()
                        }
                    )
                }
                SamService.OpprettRefusjonskravExceptions.ELEMENT_FINNES_IKKE -> {
                    val melding = "Error Id: 0, Error Message: " + response.message
                    throw OpprettRefusjonskravFaultSamordningsIdIkkeFunnetMsg(
                        melding,
                        FaultSamordningsIdIkkeFunnet().apply {
                            errorMessage = melding
                            errorSource =
                                "MODULE: nav-ent-sto-sam / COMPONENT: SamordningTOSamordningProcessProviderRemote / IF(OP): Samordning(opprettTPSamordning) / REF: SamordningProcessProviderRemotePartner IF(OP): SamordningProcessProviderRemote(samordneVedtak)"
                            dateTimeStamp = Date().toXMLGregorianCalendar()
                        }
                    )
                }
                SamService.OpprettRefusjonskravExceptions.ULOVLIG_TREKK -> {
                    val melding = "Error Id: 0, Error Message: " + response.message
                    throw OpprettRefusjonskravFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg(
                        melding,
                        FaultRefusjonskravUtenforSamordningspliktigPeriode().apply {
                            errorMessage = melding
                            errorSource =
                                "MODULE: nav-ent-sto-sam / COMPONENT: SamordningTOSamordningProcessProviderRemote / IF(OP): Samordning(opprettTPSamordning) / REF: SamordningProcessProviderRemotePartner IF(OP): SamordningProcessProviderRemote(samordneVedtak)"
                            dateTimeStamp = Date().toXMLGregorianCalendar()
                        }
                    )
                }
                else -> {
                    val melding = response.message
                    throw OpprettRefusjonskravFaultGeneriskMsg(
                        melding,
                        FaultGenerisk().also {
                            it.errorCode = "InternalError"
                            it.errorDescription = melding
                            it.errorDetails.add(melding)
                        }
                    )
                }
            }
        } catch (e: RuntimeException) {
            throwOpprettRefusjonskravFaultGeneriskMsg(e)
        }
    }

    private fun throwOpprettRefusjonskravFaultGeneriskMsg(e: Exception)  {
        val melding = e.message
        throw OpprettRefusjonskravFaultGeneriskMsg(
            melding,
            FaultGenerisk().also {
                it.errorCode = "InternalError"
                it.errorDescription = melding
                it.errorDetails.add(getMostSpecificCause(e).toString())
            }
        )
    }
}
