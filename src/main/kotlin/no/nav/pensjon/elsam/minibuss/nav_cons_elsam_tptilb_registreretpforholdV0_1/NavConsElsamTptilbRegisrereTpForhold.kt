package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.HentTPForholdListeRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdFinnTjenestepensjonsforholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdTjenestepensjonRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.inf.*
import nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnSamhandlerRequest
import nav_lib_frg.no.nav.lib.frg.gbo.GBOSamhandlerListe
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon
import nav_lib_frg.no.nav.lib.frg.inf.Samhandler
import no.nav.elsam.registreretpforhold.v0_1.*
import no.nav.pensjon.elsam.minibuss.ServiceBusinessException
import org.springframework.core.NestedExceptionUtils.*
import java.time.LocalDateTime
import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.DatatypeFactory

class NavConsElsamTptilbRegisrereTpForhold(
    private val registrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold: RegistrereTPForholdInt,
    private val SamhandlerPartner: Samhandler,
    private val registrereTPForholdV0_1IntPartner_Tjenestepensjon: RegistrereTPForholdInt
) {
    @Throws(
        ServiceBusinessException::class,
        DatatypeConfigurationException::class,
        HentTPForholdListeIntFaultTjenestepensjonForholdIkkeFunnetMsg::class,
        HentTPForholdListeIntFaultGeneriskMsg::class
    )
    fun hentTPForholdListe(hentTPForholdListeRequest: HentTPForholdListeReq): HentTPForholdListeResp {
        val response: HentTPForholdListeResp
        try {
            response = registrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold.hentTPForholdListeInt(
                HentTPForholdListeRequestInt().apply {
                    extRequest = hentTPForholdListeRequest
                })
        } catch (e: RuntimeException) {
            throw createTechnicalFault(
                e.message, getMostSpecificCause(e).toString()
            )
        }

        // Ensure that the caller has a TP-forhold to the subject
        if (!response.tjenestepensjonForholdene.any { it?.tpnr == hentTPForholdListeRequest.tpnr }) {
            throw ServiceBusinessException(
                getFaultTjenestepensjonForholdIkkeFunnet("Eget TP-nummer finnes ikke blant registrerte TP-forhold")
            )
        }

        return response
    }

    @Throws(ServiceBusinessException::class)
    fun opprettTPForhold(opprettTPForholdRequest: OpprettTPForholdReq) {
        try {
            registrereTPForholdV0_1IntPartner_Tjenestepensjon.opprettTPForholdInt(OpprettTPForholdRequestInt().apply {
                extRequest = opprettTPForholdRequest
                eksternTSSId = mapTPnrToTSSEksternId(opprettTPForholdRequest.tpnr)
            })
        } catch (e: RuntimeException) {
            throw createTechnicalFault(e.message, getMostSpecificCause(e).toString())
        } catch (e: OpprettTPForholdIntFaultElementetErDuplikatMsg) {
            // Do nothing - duplicate creates are not reported as a fault to external consumers
        }
    }

    @Throws(
        ServiceBusinessException::class,
        SlettTPForholdFinnTjenestepensjonsforholdIntFaultTjenestepensjonForholdIkkeFunnetIntMsg::class,
        SlettTPForholdFinnTjenestepensjonsforholdIntFaultGeneriskMsg::class,
        DatatypeConfigurationException::class,
        SlettTPForholdTjenestepensjonIntFaultTjenestepensjonForholdIkkeFunnetIntMsg::class
    )
    fun slettTPForhold(slettTPForholdRequest: SlettTPForholdReq) {
        val response: GBOTjenestepensjon
        try {
            response =
                registrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold.slettTPForholdFinnTjenestepensjonsforholdInt(
                    SlettTPForholdFinnTjenestepensjonsforholdRequestInt().apply {
                        extRequest = slettTPForholdRequest
                        eksternTSSId = mapTPnrToTSSEksternId(slettTPForholdRequest.tpnr)
                    })
        } catch (e: RuntimeException) {
            throw createTechnicalFault(e.message, getMostSpecificCause(e).toString())
        }
        val tpForholdene = response.tjenestepensjonForholdene
        if (tpForholdene.isEmpty()) {
            throw ServiceBusinessException(
                getFaultTjenestepensjonForholdIkkeFunnet("TP-forholdet finnes ikke i registeret")
            )
        }

        if (tpForholdene.size > 1) {
            throw createTechnicalFault("Dublett funnet, inkonsistens i registeret", "Teknisk feil")
        }

        val tpForholdet = tpForholdene[0]

        // Disallow cascade delete
        if (tpForholdet.tjenestepensjonYtelseListe.isNotEmpty()) {
            throw ServiceBusinessException(getFaultKanIkkeSlettes("TP-forholdet kan ikke slettes fordi det er registrert en eller flere TP-ytelser på forholdet."))
        }

        try {
            registrereTPForholdV0_1IntPartner_Tjenestepensjon.slettTPForholdTjenestepensjonInt(
                SlettTPForholdTjenestepensjonRequestInt().apply {
                    forholdId = tpForholdet.forholdId
                })
        } catch (e: RuntimeException) {
            throw createTechnicalFault(e.message, getMostSpecificCause(e).toString())
        }
    }

    /**
     * Map from TP-nummer to tssEksternId by searching TSS for samhandler
     * with samhandlerType TEPE, idType TPNR and the specified TP-nummer
     * Should only return one tssEksternId
     *
     * @param tpNr TP-number (4 digits)
     *
     * @return eksternTSSId (key to Samhandler)
     */
    @Throws(ServiceBusinessException::class)
    private fun mapTPnrToTSSEksternId(tpNr: String): String {
        // Build request object for samhandler

        val samhandlerResponse: GBOSamhandlerListe?
        try {
            samhandlerResponse = SamhandlerPartner.finnSamhandler(GBOFinnSamhandlerRequest().also {
                it.offentligId = tpNr
                it.idType = "TPNR"
                it.samhandlerType = "TEPE"
            })
        } catch (e: RuntimeException) {
            throw createTechnicalFault(
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "Feil ved oppslag av samhandler"
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
            //Got more than 1 samhandler in the response. Should receive only 1.
            throw createTechnicalFault(
                "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Ikke entydig treff på TP-nr"
            )
        } else if (samhandlere.size == 1) {
            val samhandler = samhandlere[0]

            //Store idTSSEkstern in class variable for later use in other methods
            val avdelinger = samhandler.avdelinger ?: throw createTechnicalFault(
                "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Fant ingen avdelinger på samhandleren"
            )
            for (avdeling in avdelinger) {
                if (avdeling != null) {
                    if (avdeling.avdelingsnr == "01") {
                        return avdeling.idTSSEkstern
                    }
                }
            }
            throw createTechnicalFault(
                "Det oppstod en feil under mapping fra TP-nummer til intern ID",
                "Ikke registrert en avdeling 01 på samhandleren"
            )
        } else {
            // Samhandler list is null-object.
            throw createTechnicalFault(
                "Det oppstod en feil under mapping fra TP-nummer til intern ID", "TP-nummeret er ikke registrert"
            )
        }
    }

    companion object {
        private fun createTechnicalFault(errorDescription: String?, errorDetail: String) =
            createTechnicalFault(errorDescription, listOf(errorDetail))

        private fun createTechnicalFault(errorDescription: String?, errorDetails: List<String>) =
            ServiceBusinessException(FaultGenerisk().apply {
                errorCode = "InternalError"
                this.errorDescription = errorDescription
                this.errorDetails.addAll(errorDetails)
            })

        @Throws(DatatypeConfigurationException::class)
        private fun getFaultTjenestepensjonForholdIkkeFunnet(errorMessage: String): FaultTjenestepensjonForholdIkkeFunnet {
            val faultBo = FaultTjenestepensjonForholdIkkeFunnet()
            faultBo.errorMessage = errorMessage
            faultBo.errorSource = "nav-cons-elsam-tptilb-registreretpforhold"
            faultBo.dateTimeStamp =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDateTime.now().toString())

            return faultBo
        }

        @Throws(DatatypeConfigurationException::class)
        private fun getFaultKanIkkeSlettes(errorMessage: String): FaultKanIkkeSlettes {
            val faultBo = FaultKanIkkeSlettes()
            faultBo.errorMessage = errorMessage
            faultBo.errorSource = "nav-cons-elsam-tptilb-registreretpforhold"
            faultBo.dateTimeStamp =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDateTime.now().toString())
            return faultBo
        }
    }
}