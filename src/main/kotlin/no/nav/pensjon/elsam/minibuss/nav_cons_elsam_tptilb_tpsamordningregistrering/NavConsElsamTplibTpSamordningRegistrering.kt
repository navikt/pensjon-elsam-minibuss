package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.*
import nav_lib_frg.no.nav.lib.frg.gbo.GBOAvdeling
import nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnSamhandlerRequest
import nav_lib_frg.no.nav.lib.frg.gbo.GBOSamhandlerListe
import nav_lib_frg.no.nav.lib.frg.inf.Samhandler
import no.nav.elsam.tpsamordningregistrering.v0_5.*
import no.nav.elsam.tpsamordningregistrering.v1_0.HentSamordningsdataResp
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResp
import no.nav.pensjon.elsam.minibuss.ServiceBusinessException
import org.springframework.core.NestedExceptionUtils
import org.springframework.stereotype.Component

@Component
class NavConsElsamTplibTpSamordningRegistrering(
    private val samhandlerPartner: Samhandler,
    private val tpSamordningRegistreringIntPartner: TPSamordningRegistreringInt
) {
    @Throws(
        LagreTPYtelseIntFaultGeneriskMsg::class,
        LagreTPYtelseIntFaultTPYtelseAlleredeRegistrertMsg::class,
        ServiceBusinessException::class
    )
    fun lagreTPYtelse(lagreTPYtelseReq: LagreTPYtelseReq): LagreTPYtelseResp {
        // Map TP-nummer to tssEksternId
        val eksternTSSId = mapTPnrToTSSEksternId(lagreTPYtelseReq.tpnr)

        // Copy external interface to internal and set externalTSSId
        val intRequest = LagreTPYtelseReqInt()
        intRequest.extRequest = lagreTPYtelseReq
        intRequest.tssEksternId = eksternTSSId

        // Call entity service
        try {
            return tpSamordningRegistreringIntPartner.lagreTPYtelseInt(intRequest)
        } catch (e: RuntimeException) {
            throw createTechnicalFault(
                INTERNALERROR, e.message, NestedExceptionUtils.getMostSpecificCause(e).toString()
            )
        }
    }

    @Throws(
        SlettTPYtelseIntFaultGeneriskMsg::class,
        SlettTPYtelseIntFaultTPYtelseIkkeFunnetMsg::class,
        ServiceBusinessException::class
    )
    fun slettTPYtelse(slettTPYtelseReq: SlettTPYtelseReq) {
        // Map TP-nummer to tssEksternId
        val eksternTSSId = mapTPnrToTSSEksternId(slettTPYtelseReq.tpnr)

        // Copy external interface to internal and set externalTSSId
        val intRequest = SlettTPYtelseReqInt()
        intRequest.extRequest = slettTPYtelseReq
        intRequest.tssEksternId = eksternTSSId

        // Call entity service
        try {
            tpSamordningRegistreringIntPartner.slettTPYtelseInt(intRequest)
        } catch (e: RuntimeException) {
            throw createTechnicalFault(
                INTERNALERROR, e.message, NestedExceptionUtils.getMostSpecificCause(e).toString()
            )
        }
    }

    @Throws(
        HentSamordningsdataIntFaultTPForholdIkkeIverksattMsg::class,
        HentSamordningsdataIntFaultGeneriskMsg::class,
        ServiceBusinessException::class
    )
    fun hentSamordningsdata(hentSamordningsdataReq: HentSamordningsdataReq): HentSamordningsdataResp {
        // Map TP-nummer to tssEksternId
        val eksternTSSId = mapTPnrToTSSEksternId(hentSamordningsdataReq.tpnr)

        // Copy external interface to internal and set externalTSSId
        val intRequest = HentSamordningsdataReqInt()
        intRequest.extRequest = hentSamordningsdataReq
        intRequest.tssEksternId = eksternTSSId

        // Call entity service
        try {
            return tpSamordningRegistreringIntPartner.hentSamordningsdataInt(intRequest)
        } catch (e: RuntimeException) {
            throw createTechnicalFault(
                INTERNALERROR, e.message, NestedExceptionUtils.getMostSpecificCause(e).toString()
            )
        }
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
    fun opprettRefusjonskrav(opprettRefusjonskravReq: OpprettRefusjonskravReq) {
        // Map TP-nummer to tssEksternId
        val eksternTSSId = mapTPnrToTSSEksternId(opprettRefusjonskravReq.tpnr)

        // Copy external interface to internal and set externalTSSId
        val intRequest = OpprettRefusjonskravReqInt()
        intRequest.extRequest = opprettRefusjonskravReq
        intRequest.tssEksternId = eksternTSSId

        // Call entity service
        try {
            tpSamordningRegistreringIntPartner.opprettRefusjonskravInt(intRequest)
        } catch (e: RuntimeException) {
            throw createTechnicalFault(
                INTERNALERROR, e.message, NestedExceptionUtils.getMostSpecificCause(e).toString()
            )
        }
    }

    /**
     * Map from TP-nummer to tssEksternId by searching TSS for samhandler with
     * samhandlerType TEPE, idType TPNR and the specified TP-nummer Should only
     * return one tssEksternId
     *
     * @param tpNr
     * TP-number (4 digits)
     *
     * @return eksternTSSId (key to Samhandler)
     */
    @Throws(ServiceBusinessException::class)
    private fun mapTPnrToTSSEksternId(tpNr: String): String {
        // Build request object for samhandler
        val samhandlerRequest = GBOFinnSamhandlerRequest()
        samhandlerRequest.offentligId = tpNr
        samhandlerRequest.idType = "TPNR"
        samhandlerRequest.samhandlerType = "TEPE"

        var samhandlerResponse: GBOSamhandlerListe?
        try {
            samhandlerResponse = samhandlerPartner.finnSamhandler(samhandlerRequest)
        } catch (e: RuntimeException) {
            throw createTechnicalFault(
                INTERNALERROR, e.message, NestedExceptionUtils.getMostSpecificCause(e).toString()
            )
        }

        if (samhandlerResponse == null) {
            throw createTechnicalFault(
                INTERNALERROR,
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "Tomt svar ved oppslag av samhandler"
            )
        }

        val samhandlere = samhandlerResponse.samhandlere
        if (samhandlere.size > 1) {
            // Got more than 1 samhandler in the response. Should receive only
            // 1.
            throw createTechnicalFault(
                INTERNALERROR,
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "Ikke entydig treff på TP-nr"
            )
        } else if (samhandlere.size == 1) {
            val samhandler = samhandlere[0]

            // Store idTSSEkstern in class variable for later use in other
            // methods
            val avdelinger = samhandler.avdelinger ?: throw createTechnicalFault(
                INTERNALERROR,
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "Fant ingen avdelinger på samhandleren"
            )
            val iter: Iterator<GBOAvdeling> = avdelinger.iterator()
            while (iter.hasNext()) {
                val avdeling = iter.next()
                if (avdeling != null) {
                    if (avdeling.avdelingsnr == "01") {
                        return avdeling.idTSSEkstern
                    }
                }
            }
            throw createTechnicalFault(
                INTERNALERROR,
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "Ikke registrert en avdeling 01 på samhandleren"
            )
        } else {
            // Samhandler list is null-object.
            throw createTechnicalFault(
                INTERNALERROR,
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "TP-nummeret er ikke registrert"
            )
        }
    }

    private fun createTechnicalFault(
        errorCode: String, errorDescription: String?, errorDetail: String
    ): ServiceBusinessException {
        return createTechnicalFault(errorCode, errorDescription, java.util.List.of(errorDetail))
    }

    private fun createTechnicalFault(
        errorCode: String, errorDescription: String?, errorDetails: List<String>
    ): ServiceBusinessException {
        val faultBO = FaultGenerisk()
        faultBO.errorCode = errorCode
        faultBO.errorDescription = errorDescription
        faultBO.errorDetails.addAll(errorDetails)
        return ServiceBusinessException(faultBO)
    }

    companion object {
        private const val INTERNALERROR = "InternalError"
    }
}
