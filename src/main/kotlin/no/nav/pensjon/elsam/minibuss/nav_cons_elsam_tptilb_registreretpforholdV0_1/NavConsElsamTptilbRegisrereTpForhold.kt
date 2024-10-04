package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.inf.*
import no.nav.elsam.registreretpforhold.v0_1.*
import no.nav.pensjon.elsam.minibuss.misc.ServiceBusinessException
import no.nav.pensjon.elsam.minibuss.misc.toXMLGregorianCalendar
import no.nav.pensjon.elsam.minibuss.tjenestepensjon.TjenestepensjonService
import org.springframework.core.NestedExceptionUtils.*
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException.Conflict
import org.springframework.web.client.HttpClientErrorException.NotFound
import java.util.*

@Component
class NavConsElsamTptilbRegisrereTpForhold(
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
            tjenestepensjonService.opprettForhold(opprettTPForholdRequest.fnr, opprettTPForholdRequest.tpnr)
        } catch (e: Conflict) {
            // Do nothing - duplicate creates are not reported as a fault to external consumers
        } catch (e: NotFound) {
            val melding = "Fant ikke person eller ordning"
            throw OpprettTPForholdFaultGeneriskMsg(
                melding,
                FaultGenerisk().apply {
                    errorCode = "InternalError"
                    errorDescription = melding
                    errorDetails.add("Teknisk feil ved oppslag")
                }
            )
        } catch (e: RuntimeException) {
            throw createTechnicalFault(e.message, getMostSpecificCause(e).toString())
        }
    }

    @Throws(
        ServiceBusinessException::class,
        SlettTPForholdFinnTjenestepensjonsforholdIntFaultTjenestepensjonForholdIkkeFunnetIntMsg::class,
        SlettTPForholdFinnTjenestepensjonsforholdIntFaultGeneriskMsg::class,
        SlettTPForholdTjenestepensjonIntFaultTjenestepensjonForholdIkkeFunnetIntMsg::class
    )
    fun slettTPForhold(slettTPForholdRequest: SlettTPForholdReq) {
        val forholdList = try {
            tjenestepensjonService.hentTjenestepensjon(slettTPForholdRequest.fnr)
                .filter { it.ordning == slettTPForholdRequest.tpnr }
                .map {
                    Forhold(
                        it.ytelser?.map { Ytelse() } ?: emptyList()
                    )
                }
        } catch (e: NotFound) {
            val melding = "Error Id: 0, Error Message: Cannot process the element bacause the ID = ${slettTPForholdRequest.fnr} do not refer to a valid element."
            throw SlettTPForholdFaultTjenestepensjonForholdIkkeFunnetMsg(
                melding,
                FaultTjenestepensjonForholdIkkeFunnet().apply {
                    errorMessage = melding
                    errorSource = "MODULE: nav-prod-frg-tp / COMPONENT: TjenestepensjonTOTjenestepensjonService / IF(OP): Tjenestepensjon(hentTjenestepensjonForholdYtelse) / REF: TjenestepensjonServicePartner IF(OP): TjenestepensjonService(hentTjenestepensjonInfo)"
                    dateTimeStamp = Date().toXMLGregorianCalendar()
                }
            )
        } catch (e: RuntimeException) {
            throw createTechnicalFault(e.message, getMostSpecificCause(e).toString())
        }
        if (forholdList.isEmpty()) {
            val melding = "TP-forholdet finnes ikke i registeret"
            throw SlettTPForholdFaultTjenestepensjonForholdIkkeFunnetMsg(
                melding,
                FaultTjenestepensjonForholdIkkeFunnet().apply {
                    errorMessage = melding
                    errorSource = "MODULE: nav-cons-elsam-tptilb-registreretpforhold / COMPONENT: authorizeAndOrchestrate / IF(OP): RegistrereTPForhold(hentTPForholdListe) / REF: SamhandlerPartner IF(OP): Samhandler(hentSamhandler)"
                    dateTimeStamp = Date().toXMLGregorianCalendar()
                }
            )
        }

        if (forholdList.size > 1) {
            throw createTechnicalFault("Dublett funnet, inkonsistens i registeret", "Teknisk feil")
        }

        val tpForholdet = forholdList[0]

        // Disallow cascade delete
        if (tpForholdet.ytelseList.isNotEmpty()) {
            val melding = "TP-forholdet kan ikke slettes fordi det er registrert en eller flere TP-ytelser på forholdet."
            throw SlettTPForholdFaultKanIkkeSlettesMsg(
                melding,
                FaultKanIkkeSlettes().apply {
                    errorMessage = melding
                    errorSource = "MODULE: nav-cons-elsam-tptilb-registreretpforhold / COMPONENT: authorizeAndOrchestrate / IF(OP): RegistrereTPForhold(hentTPForholdListe) / REF: SamhandlerPartner IF(OP): Samhandler(hentSamhandler)"
                    dateTimeStamp = Date().toXMLGregorianCalendar()
                }
            )
        }

        try {
            tjenestepensjonService.slettTjenestepensjonsforhold(
                slettTPForholdRequest.fnr,
                slettTPForholdRequest.tpnr
            )
        } catch (e: RuntimeException) {
            throw createTechnicalFault(e.message, getMostSpecificCause(e).toString())
        }
    }

    data class Forhold(val ytelseList: List<Ytelse>)

    class Ytelse

    companion object {
        private fun createTechnicalFault(errorDescription: String?, errorDetail: String) =
            createTechnicalFault(errorDescription, listOf(errorDetail))

        private fun createTechnicalFault(errorDescription: String?, errorDetails: List<String>) =
            ServiceBusinessException(FaultGenerisk().apply {
                errorCode = "InternalError"
                this.errorDescription = errorDescription
                this.errorDetails.addAll(errorDetails)
            })
    }
}
