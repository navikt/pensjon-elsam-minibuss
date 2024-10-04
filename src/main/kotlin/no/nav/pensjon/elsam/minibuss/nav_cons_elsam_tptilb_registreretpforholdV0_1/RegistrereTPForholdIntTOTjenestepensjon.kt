package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.inf.*
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.OpprettTjenestepensjonsforholdFaultStoElementetErDuplikatMsg
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.SAMTjenestepensjon
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetErDuplikat
import org.springframework.stereotype.Component

@Component
class RegistrereTPForholdIntTOTjenestepensjon(
    private val samTjenstepensjon: SAMTjenestepensjon,
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
}
