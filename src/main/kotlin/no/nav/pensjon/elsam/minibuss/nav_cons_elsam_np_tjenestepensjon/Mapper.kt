package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.fault.FaultNpPersonIkkeFunnet
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetFinnesIkke
import nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnTjenestepensjonsforholdRequest
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon
import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.DatatypeFactory


object Mapper {
    fun ASBONpHarTjenestepensjonsRequestTOGBOFinnTjenestepensjonsforholdRequest(
        ASBONpHarTjenestepensjonsforholdRequest: ASBONpHarTjenestepensjonsforholdRequest,
        GBOFinnTjenestepensjonsforholdRequest: GBOFinnTjenestepensjonsforholdRequest
    ) {
        GBOFinnTjenestepensjonsforholdRequest.fnr =
            ASBONpHarTjenestepensjonsforholdRequest.fnr // move (executionOrder=1)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultElementetFinnesIkkeTOFaultNpPersonIkkeFunnet(
        FaultElementetFinnesIkke: FaultElementetFinnesIkke, FaultNpPersonIkkeFunnet: FaultNpPersonIkkeFunnet
    ) {
        FaultNpPersonIkkeFunnet.errorMessage = FaultElementetFinnesIkke.errorMessage // move (executionOrder=1)
        FaultNpPersonIkkeFunnet.errorSource = FaultElementetFinnesIkke.errorSource // move (executionOrder=2)
        FaultNpPersonIkkeFunnet.errorType = FaultElementetFinnesIkke.errorType // move (executionOrder=3)
        FaultNpPersonIkkeFunnet.dateTimeStamp = DatatypeFactory.newInstance()
            .newXMLGregorianCalendar(FaultElementetFinnesIkke.dateTimeStamp) // move (executionOrder=4)
        FaultNpPersonIkkeFunnet.rootCause = FaultElementetFinnesIkke.rootCause // move (executionOrder=5)
    }

    fun GBOTjenestepensjonTOASBONpHarTjenestepensjonsforholdResponse(
        GBOTjenestepensjon: GBOTjenestepensjon,
        ASBONpHarTjenestepensjonsforholdResponse: ASBONpHarTjenestepensjonsforholdResponse
    ) {
        val GBOTjenestepensjon_tjenestepensjonForholdene =
            GBOTjenestepensjon.tjenestepensjonForholdene // custom.input.forEach (executionOrder=1)

        val ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold =
            if (GBOTjenestepensjon_tjenestepensjonForholdene != null && !GBOTjenestepensjon_tjenestepensjonForholdene.isEmpty()) {
                true
            } else {
                false
            } // custom.output declaration (executionOrder=1)

        ASBONpHarTjenestepensjonsforholdResponse.harTjenestepensjonsforhold =
            ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold // custom.output assignment (executionOrder=1)
    }
}
