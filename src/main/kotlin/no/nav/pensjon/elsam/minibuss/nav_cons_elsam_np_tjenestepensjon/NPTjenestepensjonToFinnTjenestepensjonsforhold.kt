package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.FinnTjenestepensjonsforholdFaultStoElementetFinnesIkkeMsg
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.SAMTjenestepensjon
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import org.springframework.stereotype.Component

@Component
class NPTjenestepensjonToFinnTjenestepensjonsforhold(
    private val finnTjenestepensjonsforhold: SAMTjenestepensjon
) {
    fun harTjenestepensjonsforhold(request: ASBONpHarTjenestepensjonsforholdRequest): ASBONpHarTjenestepensjonsforholdResponse? {
        try {
            return finnTjenestepensjonsforhold.finnTjenestepensjonsforhold(request.toGBOFinnTjenestepensjonsforholdRequest())
                ?.toASBONpHarTjenestepensjonsforholdResponse()
        } catch (e: FinnTjenestepensjonsforholdFaultStoElementetFinnesIkkeMsg) {
            throw HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg(e.message, e.faultInfo?.toFaultNpPersonIkkeFunnet())
        }
    }
}
