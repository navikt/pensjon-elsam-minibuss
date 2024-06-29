package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.*
import nav_lib_sto.no.nav.lib.sto.gbo.*
import nav_lib_sto.no.nav.lib.sto.inf.samordning.*
import no.nav.elsam.tpsamordningregistrering.v0_5.*
import no.nav.elsam.tpsamordningregistrering.v1_0.HentSamordningsdataResp
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResp
import org.springframework.stereotype.Component

@Component
class TPSamordningRegistreringIntTOSamordning(
    val samordning: Samordning,
) : TPSamordningRegistreringInt {
    override fun slettTPYtelseInt(slettTPYtelseReqInt: SlettTPYtelseReqInt) {
        try {
            val gboSlettTPSamordningRequest = GBOSlettTPSamordningRequest()
            Mapper.SlettTPYtelseReqIntTOGBOSlettTPSamordningRequest(slettTPYtelseReqInt, gboSlettTPSamordningRequest)

            samordning.slettTPSamordning(gboSlettTPSamordningRequest)
        } catch (e: SlettTPSamordningFaultPersonIkkeFunnetMsg) {
            val faultGenerisk = FaultGenerisk()

            Mapper.FaultPersonIkkeFunnetTOFaultGenerisk(e.faultInfo, faultGenerisk)

            throw SlettTPYtelseIntFaultGeneriskMsg(e.message, faultGenerisk)
        } catch (e: SlettTPSamordningFaultKombinasjonInputMsg) {
            val faultTPYtelseIkkeFunnet = FaultTPYtelseIkkeFunnet()

            Mapper.FaultKombinasjonInputTOFaultTPYtelseIkkeFunnet(e.faultInfo, faultTPYtelseIkkeFunnet)

            throw SlettTPYtelseIntFaultTPYtelseIkkeFunnetMsg(e.message, faultTPYtelseIkkeFunnet)
        }
    }

    override fun hentSamordningsdataInt(hentSamordningsdataReqInt: HentSamordningsdataReqInt?): HentSamordningsdataResp {
        try {
            val gboHentSamordningsdataRequest = GBOHentSamordningsdataRequest()
            Mapper.HentSamordningsdataReqIntTOGBOHentSamordningsdataRequest(
                hentSamordningsdataReqInt,
                gboHentSamordningsdataRequest
            )

            val hentSamordningsdata: GBOSamordningsdata = samordning.hentSamordningsdata(gboHentSamordningsdataRequest)

            val hentSamordningsdataResp = HentSamordningsdataResp()
            Mapper.GBOSamordningsdataTOHentSamordningsdataResp(hentSamordningsdata, hentSamordningsdataResp)
            return hentSamordningsdataResp
        } catch (e: HentSamordningsdataFaultYtelseIkkeIverksattMsg) {
            val faultTPForholdIkkeIverksatt = FaultTPForholdIkkeIverksatt()

            Mapper.FaultYtelseIkkeIverksattTOFaultTPForholdIkkeIverksatt(e.faultInfo, faultTPForholdIkkeIverksatt)

            throw HentSamordningsdataIntFaultTPForholdIkkeIverksattMsg(e.message, faultTPForholdIkkeIverksatt)
        } catch (e: HentSamordningsdataFaultPersonIkkeFunnetMsg) {
            val faultGenerisk = FaultGenerisk()

            Mapper.FaultPersonIkkeFunnetTOFaultGenerisk(e.faultInfo, faultGenerisk)

            throw HentSamordningsdataIntFaultGeneriskMsg(e.message, faultGenerisk)
        }
    }

    override fun opprettRefusjonskravInt(opprettRefusjonskravReqInt: OpprettRefusjonskravReqInt?) {
        val gboOpprettRefusjonskravRequest = GBOOpprettRefusjonskravRequest()
        Mapper.OpprettRefusjonskravReqIntTOGBOOpprettRefusjonskravRequest(opprettRefusjonskravReqInt, gboOpprettRefusjonskravRequest)

        try {
            samordning.opprettRefusjonskrav(gboOpprettRefusjonskravRequest)
        } catch (e: OpprettRefusjonskravFaultSamIdIkkeGyldigMsg) {
            val faultSamordningsIdIkkeFunnet = FaultSamordningsIdIkkeFunnet()

            Mapper.FaultSamIdIkkeGyldigTOFaultSamordningsIdIkkeFunnet(e.faultInfo, faultSamordningsIdIkkeFunnet)

            throw OpprettRefusjonskravIntFaultSamordningsIdIkkeFunnetMsg(e.message, faultSamordningsIdIkkeFunnet)

        } catch (e: OpprettRefusjonskravFaultSvarUtenforPeriodeMsg) {
            val faultRefusjonskravUtenforSamordningspliktigPeriode = FaultRefusjonskravUtenforSamordningspliktigPeriode()

            Mapper.FaultSvarUtenforPeriodeTOFaultRefusjonskravUtenforSamordningspliktigPeriode(e.faultInfo, faultRefusjonskravUtenforSamordningspliktigPeriode)

            throw OpprettRefusjonskravIntFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg(e.message, faultRefusjonskravUtenforSamordningspliktigPeriode)

        } catch (e: OpprettRefusjonskravFaultRefKravAlleredeRegElUtenforFrist1Msg) {
            val faultAlleredeMottattRefusjonskrav = FaultAlleredeMottattRefusjonskrav()

            Mapper.FaultRefKravAlleredeRegElUtenforFristTOFaultAlleredeMottattRefusjonskrav(e.faultInfo, faultAlleredeMottattRefusjonskrav)

            throw OpprettRefusjonskravIntFaultAlleredeMottattRefusjonskravMsg(e.message, faultAlleredeMottattRefusjonskrav)
        }
    }

    override fun lagreTPYtelseInt(lagreTPYtelseReqInt: LagreTPYtelseReqInt?): LagreTPYtelseResp {
        try {
            val gboOpprettTPSamordningRequest = GBOOpprettTPSamordningRequest()
            Mapper.LagreTPYtelseReqIntTOGBOOpprettTPSamordningRequest(lagreTPYtelseReqInt, gboOpprettTPSamordningRequest)

            val opprettTPSamordning: GBOSamordningsdata = samordning.opprettTPSamordning(gboOpprettTPSamordningRequest)

            val lagreTPYtelseResp = LagreTPYtelseResp()
            Mapper.GBOSamordningsdataTOLagreTPYtelseResp(opprettTPSamordning, lagreTPYtelseResp)

            return lagreTPYtelseResp
        } catch (e: OpprettTPSamordningFaultPersonIkkeFunnetMsg) {
            val faultGenerisk = FaultGenerisk()

            Mapper.FaultPersonIkkeFunnetTOFaultGenerisk(e.faultInfo, faultGenerisk)

            throw LagreTPYtelseIntFaultGeneriskMsg(e.message, faultGenerisk)
        } catch (e: OpprettTPSamordningFaultYtelseAlleredeRegistrertMsg) {
            val faultTPYtelseAlleredeRegistrert = FaultTPYtelseAlleredeRegistrert()

            Mapper.FaultYtelseAlleredeRegistrertTOFaultTPYtelseAlleredeRegistrert(e.faultInfo, faultTPYtelseAlleredeRegistrert)

            throw LagreTPYtelseIntFaultTPYtelseAlleredeRegistrertMsg(e.message, faultTPYtelseAlleredeRegistrert)
        }
    }
}
