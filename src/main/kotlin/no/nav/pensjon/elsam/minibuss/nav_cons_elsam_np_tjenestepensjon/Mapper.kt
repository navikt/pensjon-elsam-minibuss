package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.fault.FaultNpPersonIkkeFunnet
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetFinnesIkke
import nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnTjenestepensjonsforholdRequest
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon
import javax.xml.datatype.DatatypeFactory

// ASBONpHarTjenestepensjonsRequestTOGBOFinnTjenestepensjonsforholdRequest
fun ASBONpHarTjenestepensjonsforholdRequest.toGBOFinnTjenestepensjonsforholdRequest() =
    GBOFinnTjenestepensjonsforholdRequest().also {
        it.fnr = fnr // move (executionOrder=1)
    }

// FaultElementetFinnesIkkeTOFaultNpPersonIkkeFunnet
fun FaultElementetFinnesIkke.toFaultNpPersonIkkeFunnet(): FaultNpPersonIkkeFunnet =
    FaultNpPersonIkkeFunnet().also {
        it.errorMessage = errorMessage // move (executionOrder=1)
        it.errorSource = errorSource // move (executionOrder=2)
        it.errorType = errorType // move (executionOrder=3)
        it.dateTimeStamp = dateTimeStamp?.let { DatatypeFactory.newInstance().newXMLGregorianCalendar(it) } // move (executionOrder=4)
        it.rootCause = rootCause // move (executionOrder=5)
    }

// GBOTjenestepensjonTOASBONpHarTjenestepensjonsforholdResponse
fun GBOTjenestepensjon.toASBONpHarTjenestepensjonsforholdResponse(): ASBONpHarTjenestepensjonsforholdResponse =
    ASBONpHarTjenestepensjonsforholdResponse().also {
        it.harTjenestepensjonsforhold = tjenestepensjonForholdene.isNotEmpty() // custom.output assignment (executionOrder=1)
    }
