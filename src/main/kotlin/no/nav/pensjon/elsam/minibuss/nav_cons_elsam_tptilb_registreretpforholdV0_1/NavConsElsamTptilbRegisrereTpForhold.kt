package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdFinnTjenestepensjonsforholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdTjenestepensjonRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.inf.*
import nav_cons_pen_psak_samhandler.no.nav.inf.PSAKSamhandler
import nav_lib_cons_pen_psakpselv.no.nav.lib.pen.psakpselv.asbo.samhandler.ASBOPenFinnSamhandlerRequest
import nav_lib_cons_pen_psakpselv.no.nav.lib.pen.psakpselv.asbo.samhandler.ASBOPenSamhandlerListe
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoTjenestepensjon
import no.nav.elsam.registreretpforhold.v0_1.*
import no.nav.pensjon.elsam.minibuss.misc.ServiceBusinessException
import no.nav.pensjon.elsam.minibuss.misc.toXMLGregorianCalendar
import no.nav.pensjon.elsam.minibuss.tjenestepensjon.TjenestepensjonService
import org.springframework.core.NestedExceptionUtils.*
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException.NotFound
import java.time.LocalDateTime
import java.util.*
import javax.xml.datatype.DatatypeFactory

@Component
class NavConsElsamTptilbRegisrereTpForhold(
    private val registrereTPForholdInt: RegistrereTPForholdIntTOTjenestepensjon,
    private val samhandler: PSAKSamhandler,
    private val tjenestepensjonService: TjenestepensjonService,
) {
    @Throws(
        ServiceBusinessException::class,
        HentTPForholdListeIntFaultTjenestepensjonForholdIkkeFunnetMsg::class,
        HentTPForholdListeIntFaultGeneriskMsg::class
    )
    fun hentTPForholdListe(request: HentTPForholdListeReq): HentTPForholdListeResp {
        val response: HentTPForholdListeResp
        try {
            response = HentTPForholdListeResp().apply {
                tjenestepensjonForholdene.addAll(
                    tjenestepensjonService.hentTjenestepensjon(request.fnr)
                        .map {
                            TPForhold().apply {
                                tpnr = it.ordning
                                tpnavn = tjenestepensjonService.hentOrdning(it.ordning)
                            }
                        }
                )
            }
        } catch (e: NotFound) {
            val melding = "Error Id: 0, Error Message: Cannot process the element bacause the ID = ${request.fnr} do not refer to a valid element."
            throw HentTPForholdListeFaultTjenestepensjonForholdIkkeFunnetMsg(
                melding,
                FaultTjenestepensjonForholdIkkeFunnet().apply {
                    errorMessage = melding
                    errorSource = "MODULE: nav-prod-frg-tp / COMPONENT: TjenestepensjonTOTjenestepensjonService / IF(OP): Tjenestepensjon(hentTjenestepensjonForholdYtelse) / REF: TjenestepensjonServicePartner IF(OP): TjenestepensjonService(hentTjenestepensjonInfo)"
                    dateTimeStamp = Date().toXMLGregorianCalendar()
                }
            )
        }

        // Ensure that the caller has a TP-forhold to the subject
        if (!response.tjenestepensjonForholdene.any { it?.tpnr == request.tpnr }) {
            val melding = "Eget TP-nummer finnes ikke blant registrerte TP-forhold"

            throw HentTPForholdListeFaultTjenestepensjonForholdIkkeFunnetMsg(
                melding,
                FaultTjenestepensjonForholdIkkeFunnet().apply {
                    errorMessage = melding
                    errorSource = "MODULE: nav-cons-elsam-tptilb-registreretpforhold / COMPONENT: authorizeAndOrchestrate / IF(OP): RegistrereTPForhold(hentTPForholdListe) / REF: SamhandlerPartner IF(OP): Samhandler(hentSamhandler)"
                    dateTimeStamp = Date().toXMLGregorianCalendar()
                }
            )
        }

        return response
    }

    @Throws(ServiceBusinessException::class)
    fun opprettTPForhold(opprettTPForholdRequest: OpprettTPForholdReq) {
        try {
            registrereTPForholdInt.opprettTPForholdInt(OpprettTPForholdRequestInt().apply {
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
        SlettTPForholdTjenestepensjonIntFaultTjenestepensjonForholdIkkeFunnetIntMsg::class
    )
    fun slettTPForhold(slettTPForholdRequest: SlettTPForholdReq) {
        val response: ASBOStoTjenestepensjon
        try {
            response =
                registrereTPForholdInt.slettTPForholdFinnTjenestepensjonsforholdInt(
                    SlettTPForholdFinnTjenestepensjonsforholdRequestInt().apply {
                        extRequest = slettTPForholdRequest
                        eksternTSSId = mapTPnrToTSSEksternId(slettTPForholdRequest.tpnr)
                    })
        } catch (e: RuntimeException) {
            throw createTechnicalFault(e.message, getMostSpecificCause(e).toString())
        }
        val tpForholdene = response.tjenestepensjonsforholdListe
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
            registrereTPForholdInt.slettTPForholdTjenestepensjonInt(tpForholdet.forholdId, slettTPForholdRequest.fnr, slettTPForholdRequest.tpnr)
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

        val samhandlerResponse: ASBOPenSamhandlerListe?
        try {
            samhandlerResponse = samhandler.finnSamhandler(ASBOPenFinnSamhandlerRequest().also {
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

        private fun getFaultTjenestepensjonForholdIkkeFunnet(errorMessage: String): FaultTjenestepensjonForholdIkkeFunnet {
            val faultBo = FaultTjenestepensjonForholdIkkeFunnet()
            faultBo.errorMessage = errorMessage
            faultBo.errorSource = "nav-cons-elsam-tptilb-registreretpforhold"
            faultBo.dateTimeStamp =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDateTime.now().toString())

            return faultBo
        }

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
