package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdFinnTjenestepensjonsforholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdTjenestepensjonRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.inf.*
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.OpprettTjenestepensjonsforholdFaultStoElementetErDuplikatMsg
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.SAMTjenestepensjon
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoTjenestepensjon
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetErDuplikat
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.*
import org.springframework.stereotype.Component

@Component
class RegistrereTPForholdIntTOTjenestepensjon(
    private val finnTjenestepensjonsforhold: SAMTjenestepensjon,
    private val samTjenstepensjon: SAMTjenestepensjon,
    private val tjenestepensjon: Tjenestepensjon,
) {
    // RegistrereTPForholdIntTOTjenestepensjon
    fun opprettTPForholdInt(opprettTPForholdRequestInt: OpprettTPForholdRequestInt) {
        try {
            samTjenstepensjon.opprettTjenestepensjonsforhold(opprettTPForholdRequestInt.toGBOTjenestepensjon())
        } catch (e: OpprettTjenestepensjonsforholdFaultStoElementetErDuplikatMsg) {
            throw OpprettTPForholdIntFaultElementetErDuplikatMsg(e.message, e.faultInfo?.let {
                FaultElementetErDuplikat().apply {
                    errorMessage = it.errorMessage
                    errorSource = it.errorSource
                    errorType = it.errorType
                    rootCause = it.rootCause
                    dateTimeStamp = it.dateTimeStamp.toString()
                }
            })
        }
    }

    // RegistrereTPForholdIntTOFinnTjenestepensjonsforhold
    fun slettTPForholdFinnTjenestepensjonsforholdInt(slettTPForholdRequestInt: SlettTPForholdFinnTjenestepensjonsforholdRequestInt): ASBOStoTjenestepensjon {
        try {
            return finnTjenestepensjonsforhold.finnTjenestepensjonsforhold(slettTPForholdRequestInt.toGBOFinnTjenestepensjonsforholdRequest())
        } catch (e: HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg) {
            throw SlettTPForholdFinnTjenestepensjonsforholdIntFaultTjenestepensjonForholdIkkeFunnetIntMsg(e.message, e.faultInfo?.toFaultTjenestepensjonForholdIkkeFunnet())
        } catch (e: HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg) {
            throw SlettTPForholdFinnTjenestepensjonsforholdIntFaultGeneriskMsg(e.message, e.faultInfo?.toFaultGenerisk())
        }
    }

    // RegistrereTPForholdIntTOTjenestepensjon
    fun slettTPForholdTjenestepensjonInt(slettTPForholdRequestInt: SlettTPForholdTjenestepensjonRequestInt) {
        try {
            tjenestepensjon.slettTjenestepensjonsforhold(slettTPForholdRequestInt.toGBOTjenestepensjonForhold())
        } catch (e: SlettTjenestepensjonsforholdFaultElementetErUgyldigMsg) {
            throw SlettTPForholdTjenestepensjonIntFaultTjenestepensjonForholdIkkeFunnetIntMsg(e.message, e.faultInfo?.toFaultTjenestepensjonForholdIkkeFunnet())
        }
    }
}
