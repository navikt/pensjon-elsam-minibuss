package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.*
import nav_lib_sto.no.nav.lib.sto.inf.samordning.*
import no.nav.elsam.tpsamordningregistrering.v1_0.HentSamordningsdataResp
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResp
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultAlleredeMottattRefusjonskrav
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultGenerisk
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultRefusjonskravUtenforSamordningspliktigPeriode
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultSamordningsIdIkkeFunnet
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultTPForholdIkkeIverksatt
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultTPYtelseAlleredeRegistrert
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toFaultTPYtelseIkkeFunnet
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toGBOHentSamordningsdataRequest
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toGBOOpprettRefusjonskravRequest
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toGBOOpprettTPSamordningRequest
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toGBOSlettTPSamordningRequest
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toHentSamordningsdataResp
import no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering.Mapper.toLagreTPYtelseResp
import org.springframework.stereotype.Component

@Component
class TPSamordningRegistreringIntTOSamordning(
    val samordning: Samordning,
) : TPSamordningRegistreringInt {
    override fun slettTPYtelseInt(slettTPYtelseReqInt: SlettTPYtelseReqInt) {
        try {
            samordning.slettTPSamordning(slettTPYtelseReqInt.toGBOSlettTPSamordningRequest())
        } catch (e: SlettTPSamordningFaultPersonIkkeFunnetMsg) {
            throw SlettTPYtelseIntFaultGeneriskMsg(e.message, e.faultInfo?.toFaultGenerisk())
        } catch (e: SlettTPSamordningFaultKombinasjonInputMsg) {
            throw SlettTPYtelseIntFaultTPYtelseIkkeFunnetMsg(e.message, e.faultInfo?.toFaultTPYtelseIkkeFunnet())
        }
    }

    override fun hentSamordningsdataInt(hentSamordningsdataReqInt: HentSamordningsdataReqInt): HentSamordningsdataResp {
        try {
            return samordning.hentSamordningsdata(hentSamordningsdataReqInt.toGBOHentSamordningsdataRequest())
                .toHentSamordningsdataResp()
        } catch (e: HentSamordningsdataFaultYtelseIkkeIverksattMsg) {
            throw HentSamordningsdataIntFaultTPForholdIkkeIverksattMsg(
                e.message,
                e.faultInfo?.toFaultTPForholdIkkeIverksatt()
            )
        } catch (e: HentSamordningsdataFaultPersonIkkeFunnetMsg) {
            throw HentSamordningsdataIntFaultGeneriskMsg(e.message, e.faultInfo?.toFaultGenerisk())
        }
    }

    override fun opprettRefusjonskravInt(opprettRefusjonskravReqInt: OpprettRefusjonskravReqInt) {
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

    override fun lagreTPYtelseInt(lagreTPYtelseReqInt: LagreTPYtelseReqInt): LagreTPYtelseResp {
        try {
            return samordning.opprettTPSamordning(lagreTPYtelseReqInt.toGBOOpprettTPSamordningRequest())
                .toLagreTPYtelseResp()
        } catch (e: OpprettTPSamordningFaultPersonIkkeFunnetMsg) {
            throw LagreTPYtelseIntFaultGeneriskMsg(e.message, e.faultInfo?.toFaultGenerisk())
        } catch (e: OpprettTPSamordningFaultYtelseAlleredeRegistrertMsg) {
            throw LagreTPYtelseIntFaultTPYtelseAlleredeRegistrertMsg(
                e.message, e.faultInfo?.toFaultTPYtelseAlleredeRegistrert()
            )
        }
    }
}
