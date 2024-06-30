package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.HentTPForholdListeRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdFinnTjenestepensjonsforholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdTjenestepensjonRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.inf.*
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.*
import no.nav.elsam.registreretpforhold.v0_1.HentTPForholdListeResp
import no.nav.pensjon.elsam.minibuss.nav_bsrv_frg_finntjenestepensjonsforhold.NavBsrvFrgFinnTjenestepensjonsforhold
import org.springframework.stereotype.Component

@Component
class RegistrereTPForholdIntTOTjenestepensjon(
    private val finnTjenestepensjonsforhold: NavBsrvFrgFinnTjenestepensjonsforhold,
    private val tjenstepensjon: Tjenestepensjon,
) : RegistrereTPForholdInt {
    // RegistrereTPForholdIntTOTjenestepensjon
    override fun opprettTPForholdInt(opprettTPForholdRequestInt: OpprettTPForholdRequestInt) {
        try {
            tjenstepensjon.opprettTjenestepensjonsforhold(opprettTPForholdRequestInt.toGBOTjenestepensjon())
        } catch (e: OpprettTjenestepensjonsforholdFaultElementetErDuplikatMsg) {
            throw OpprettTPForholdIntFaultElementetErDuplikatMsg(e.message, e.faultInfo)
        }
    }

    // RegistrereTPForholdIntTOFinnTjenestepensjonsforhold
    override fun slettTPForholdFinnTjenestepensjonsforholdInt(slettTPForholdRequestInt: SlettTPForholdFinnTjenestepensjonsforholdRequestInt): GBOTjenestepensjon? {
        try {
            return finnTjenestepensjonsforhold.finnTjenestepensjonsforhold(slettTPForholdRequestInt.toGBOFinnTjenestepensjonsforholdRequest())
        } catch (e: HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg) {
            throw SlettTPForholdFinnTjenestepensjonsforholdIntFaultTjenestepensjonForholdIkkeFunnetIntMsg(e.message, e.faultInfo?.toFaultTjenestepensjonForholdIkkeFunnet())
        } catch (e: HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg) {
            throw SlettTPForholdFinnTjenestepensjonsforholdIntFaultGeneriskMsg(e.message, e.faultInfo?.toFaultGenerisk())
        }
    }

    // RegistrereTPForholdIntTOFinnTjenestepensjonsforhold
    override fun hentTPForholdListeInt(hentTPForholdListeRequestInt: HentTPForholdListeRequestInt): HentTPForholdListeResp? {
        try {
            return finnTjenestepensjonsforhold.finnTjenestepensjonsforhold(hentTPForholdListeRequestInt.toGBOFinnTjenestepensjonsforholdRequest())?.toHentTPForholdListeResp()
        } catch (e: HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg) {
            throw HentTPForholdListeIntFaultTjenestepensjonForholdIkkeFunnetMsg(e.message, e.faultInfo?.toFaultTjenestepensjonForholdIkkeFunnet())
        } catch (e: HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg) {
            throw HentTPForholdListeIntFaultGeneriskMsg(e.message, e.faultInfo?.toFaultGenerisk())
        }
    }

    // RegistrereTPForholdIntTOTjenestepensjon
    override fun slettTPForholdTjenestepensjonInt(slettTPForholdRequestInt: SlettTPForholdTjenestepensjonRequestInt) {
        try {
            tjenstepensjon.slettTjenestepensjonsforhold(slettTPForholdRequestInt.toGBOTjenestepensjonForhold())
        } catch (e: SlettTjenestepensjonsforholdFaultElementetErUgyldigMsg) {
            throw SlettTPForholdTjenestepensjonIntFaultTjenestepensjonForholdIkkeFunnetIntMsg(e.message, e.faultInfo?.toFaultTjenestepensjonForholdIkkeFunnet())
        }
    }
}
