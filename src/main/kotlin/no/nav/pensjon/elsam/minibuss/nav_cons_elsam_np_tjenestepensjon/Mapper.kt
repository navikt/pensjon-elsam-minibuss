package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon;


import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest;
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse;
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.fault.FaultNpPersonIkkeFunnet;
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjonForhold;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.List;

public class Mapper {
    public static void ASBONpHarTjenestepensjonsRequestTOGBOFinnTjenestepensjonsforholdRequest(ASBONpHarTjenestepensjonsforholdRequest ASBONpHarTjenestepensjonsforholdRequest, nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnTjenestepensjonsforholdRequest GBOFinnTjenestepensjonsforholdRequest) {
        GBOFinnTjenestepensjonsforholdRequest.setFnr(ASBONpHarTjenestepensjonsforholdRequest.getFnr()); // move (executionOrder=1)
    }

    public static void FaultElementetFinnesIkkeTOFaultNpPersonIkkeFunnet(nav_lib_frg.no.nav.lib.frg.fault.FaultElementetFinnesIkke FaultElementetFinnesIkke, FaultNpPersonIkkeFunnet FaultNpPersonIkkeFunnet) throws DatatypeConfigurationException {
        FaultNpPersonIkkeFunnet.setErrorMessage(FaultElementetFinnesIkke.getErrorMessage()); // move (executionOrder=1)
        FaultNpPersonIkkeFunnet.setErrorSource(FaultElementetFinnesIkke.getErrorSource()); // move (executionOrder=2)
        FaultNpPersonIkkeFunnet.setErrorType(FaultElementetFinnesIkke.getErrorType()); // move (executionOrder=3)
        FaultNpPersonIkkeFunnet.setDateTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(FaultElementetFinnesIkke.getDateTimeStamp())); // move (executionOrder=4)
        FaultNpPersonIkkeFunnet.setRootCause(FaultElementetFinnesIkke.getRootCause()); // move (executionOrder=5)
    }

    public static void GBOTjenestepensjonTOASBONpHarTjenestepensjonsforholdResponse(nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon GBOTjenestepensjon, ASBONpHarTjenestepensjonsforholdResponse ASBONpHarTjenestepensjonsforholdResponse) {
        List<GBOTjenestepensjonForhold> GBOTjenestepensjon_tjenestepensjonForholdene = GBOTjenestepensjon.getTjenestepensjonForholdene(); // custom.input.forEach (executionOrder=1)
        boolean ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold; // custom.output declaration (executionOrder=1)

        if (GBOTjenestepensjon_tjenestepensjonForholdene != null && !GBOTjenestepensjon_tjenestepensjonForholdene.isEmpty()) {
            ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold = true;
        } else {
            ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold = false;
        }

        ASBONpHarTjenestepensjonsforholdResponse.setHarTjenestepensjonsforhold(ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold); // custom.output assignment (executionOrder=1)
    }
}
