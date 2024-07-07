package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.fault.FaultNpPersonIkkeFunnet
import no.nav.pensjon.elsam.minibuss.misc.toXMLGregorianCalendar
import no.nav.pensjon.elsam.minibuss.tjenestepensjon.TjenestepensjonService
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException.NotFound
import java.util.Date

@Component
class NPTjenestepensjonToFinnTjenestepensjonsforhold(
    private val tjenestepensjonService: TjenestepensjonService,
) {
    fun harTjenestepensjonsforhold(request: ASBONpHarTjenestepensjonsforholdRequest) =
        try {
            ASBONpHarTjenestepensjonsforholdResponse().apply {
                harTjenestepensjonsforhold = tjenestepensjonService.harTjenestepensjon(request.fnr)
            }
        } catch (e: NotFound) {
            val message = "Error Id: 0, Error Message: Cannot process the element bacause the ID = ${request.fnr} do not refer to a valid element."
            throw HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg(
                message,
                FaultNpPersonIkkeFunnet().apply {
                    errorMessage = message
                    errorSource = "MODULE: nav-prod-frg-tp / COMPONENT: TjenestepensjonTOTjenestepensjonService / IF(OP): Tjenestepensjon(hentTjenestepensjonForholdYtelse) / REF: TjenestepensjonServicePartner IF(OP): TjenestepensjonService(hentTjenestepensjonInfo)"
                    errorType = "Business"
                    dateTimeStamp = Date().toXMLGregorianCalendar()
                }
            )
        }
}
