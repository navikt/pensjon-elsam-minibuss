package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import no.nav.pensjon.elsam.minibuss.tjenestepensjon.TjenestepensjonService
import org.springframework.stereotype.Component

@Component
class NPTjenestepensjonToFinnTjenestepensjonsforhold(
    private val tjenestepensjonService: TjenestepensjonService,
) {
    fun harTjenestepensjonsforhold(request: ASBONpHarTjenestepensjonsforholdRequest) =
        ASBONpHarTjenestepensjonsforholdResponse().apply {
            harTjenestepensjonsforhold = tjenestepensjonService.harTjenestepensjon(request.fnr)
        }
}
