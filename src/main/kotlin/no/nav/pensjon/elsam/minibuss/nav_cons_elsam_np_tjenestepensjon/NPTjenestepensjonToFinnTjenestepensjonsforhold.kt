package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg
import no.nav.pensjon.elsam.minibuss.nav_bsrv_frg_finntjenestepensjonsforhold.NavBsrvFrgFinnTjenestepensjonsforhold
import org.springframework.stereotype.Component

@Component
class NPTjenestepensjonToFinnTjenestepensjonsforhold(
    private val finnTjenestepensjonsforhold: NavBsrvFrgFinnTjenestepensjonsforhold
) {
    fun harTjenestepensjonsforhold(request: ASBONpHarTjenestepensjonsforholdRequest): ASBONpHarTjenestepensjonsforholdResponse? {
        try {
            return finnTjenestepensjonsforhold.finnTjenestepensjonsforhold(request.toGBOFinnTjenestepensjonsforholdRequest())
                ?.toASBONpHarTjenestepensjonsforholdResponse()
        } catch (e: HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg) {
            throw HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg(e.message, e.faultInfo?.toFaultNpPersonIkkeFunnet())
        }
    }
}
