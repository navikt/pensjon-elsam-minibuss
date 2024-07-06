package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import io.getunleash.Unleash
import nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.FinnTjenestepensjonsforholdFaultStoElementetFinnesIkkeMsg
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.SAMTjenestepensjon
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import no.nav.pensjon.elsam.minibuss.misc.entries
import no.nav.pensjon.elsam.minibuss.tjenestepensjon.TjenestepensjonService
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Component

@Component
class NPTjenestepensjonToFinnTjenestepensjonsforhold(
    private val finnTjenestepensjonsforhold: SAMTjenestepensjon,
    private val tjenestepensjonService: TjenestepensjonService,
    private val unleash: Unleash,
) {
    private val logger: Logger = getLogger(javaClass)

    fun harTjenestepensjonsforhold(request: ASBONpHarTjenestepensjonsforholdRequest): ASBONpHarTjenestepensjonsforholdResponse? {
        try {
            val responseTp: Boolean? = if (unleash.isEnabled("pensjon-elsam-minibuss.harTjenestepensjonsforhold.sjekk-tp")) {
                hentStatusTp(request.fnr)
            } else {
                null
            }

            val responseBus = finnTjenestepensjonsforhold.finnTjenestepensjonsforhold(request.toGBOFinnTjenestepensjonsforholdRequest())
                    ?.toASBONpHarTjenestepensjonsforholdResponse()

            if (responseBus?.harTjenestepensjonsforhold != responseTp) {
                logger.info(
                    "Avvik mellom buss og tp, {}", entries(
                        "bus" to responseBus?.harTjenestepensjonsforhold,
                        "tp" to responseTp,
                    )
                )
            }

            return responseBus
        } catch (e: FinnTjenestepensjonsforholdFaultStoElementetFinnesIkkeMsg) {
            throw HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg(e.message, e.faultInfo?.toFaultNpPersonIkkeFunnet())
        }
    }

    private fun hentStatusTp(
        fnr: String,
    ) =
        try {
            tjenestepensjonService.harTjenestepensjon(fnr)
        } catch (e: Exception) {
            logger.error("Feil ved henting av tjenestepensjon", e)
            null
        }
}
