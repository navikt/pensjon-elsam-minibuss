package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.*
import nav_lib_sto.no.nav.lib.sto.inf.samordning.*
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultAlleredeMottattRefusjonskrav
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultRefusjonskravUtenforSamordningspliktigPeriode
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultSamordningsIdIkkeFunnet
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toGBOOpprettRefusjonskravRequest
import org.springframework.stereotype.Component

@Component
class TPSamordningRegistreringIntTOSamordning(
    val samordning: Samordning,
) {
    fun opprettRefusjonskravInt(opprettRefusjonskravReqInt: OpprettRefusjonskravReqInt) {
        try {
            samordning.opprettRefusjonskrav(opprettRefusjonskravReqInt.toGBOOpprettRefusjonskravRequest())
        } catch (e: OpprettRefusjonskravFaultSamIdIkkeGyldigMsg) {
            throw OpprettRefusjonskravIntFaultSamordningsIdIkkeFunnetMsg(
                e.message,
                e.faultInfo?.toFaultSamordningsIdIkkeFunnet()
            )
        } catch (e: OpprettRefusjonskravFaultSvarUtenforPeriodeMsg) {
            throw OpprettRefusjonskravIntFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg(
                e.message,
                e.faultInfo?.toFaultRefusjonskravUtenforSamordningspliktigPeriode()
            )
        } catch (e: OpprettRefusjonskravFaultRefKravAlleredeRegElUtenforFrist1Msg) {
            throw OpprettRefusjonskravIntFaultAlleredeMottattRefusjonskravMsg(
                e.message,
                e.faultInfo?.toFaultAlleredeMottattRefusjonskrav()
            )
        }
    }
}
