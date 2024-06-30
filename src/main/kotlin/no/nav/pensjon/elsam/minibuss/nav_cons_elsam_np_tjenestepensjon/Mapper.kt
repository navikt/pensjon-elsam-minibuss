package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.fault.FaultNpPersonIkkeFunnet
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoFinnTjenestepensjonsforholdRequest
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoTjenestepensjon
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.fault.tjenestepensjon.FaultStoElementetFinnesIkke
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetFinnesIkke
import nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnTjenestepensjonsforholdRequest
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon
import javax.xml.datatype.DatatypeFactory

// ASBONpHarTjenestepensjonsRequestTOGBOFinnTjenestepensjonsforholdRequest
fun ASBONpHarTjenestepensjonsforholdRequest.toGBOFinnTjenestepensjonsforholdRequest() =
    ASBOStoFinnTjenestepensjonsforholdRequest().also {
        it.fnr = fnr // move (executionOrder=1)
    }

// FaultElementetFinnesIkkeTOFaultNpPersonIkkeFunnet
fun FaultStoElementetFinnesIkke.toFaultNpPersonIkkeFunnet(): FaultNpPersonIkkeFunnet =
    FaultNpPersonIkkeFunnet().also {
        it.errorMessage = errorMessage // move (executionOrder=1)
        it.errorSource = errorSource // move (executionOrder=2)
        it.errorType = errorType // move (executionOrder=3)
        it.dateTimeStamp = dateTimeStamp // move (executionOrder=4)
        it.rootCause = rootCause // move (executionOrder=5)
    }

// GBOTjenestepensjonTOASBONpHarTjenestepensjonsforholdResponse
fun ASBOStoTjenestepensjon.toASBONpHarTjenestepensjonsforholdResponse(): ASBONpHarTjenestepensjonsforholdResponse =
    ASBONpHarTjenestepensjonsforholdResponse().also {
        it.harTjenestepensjonsforhold = tjenestepensjonsforholdListe.isNotEmpty() // custom.output assignment (executionOrder=1)
    }
