package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon;


import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest;
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse;
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.fault.FaultNpPersonIkkeFunnet;

import java.util.List;

public class Mapper {
    public static void ASBONpHarTjenestepensjonsRequestTOGBOFinnTjenestepensjonsforholdRequest(ASBONpHarTjenestepensjonsforholdRequest ASBONpHarTjenestepensjonsforholdRequest, nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnTjenestepensjonsforholdRequest GBOFinnTjenestepensjonsforholdRequest) {
        GBOFinnTjenestepensjonsforholdRequest.setFnr(ASBONpHarTjenestepensjonsforholdRequest.getFnr()); // move (executionOrder=1)
    }

    public static void FaultElementetFinnesIkkeTOFaultNpPersonIkkeFunnet(nav_lib_frg.no.nav.lib.frg.fault.FaultElementetFinnesIkke FaultElementetFinnesIkke, FaultNpPersonIkkeFunnet FaultNpPersonIkkeFunnet) {
        FaultNpPersonIkkeFunnet.setErrorMessage(FaultElementetFinnesIkke.getErrorMessage()); // move (executionOrder=1)
        FaultNpPersonIkkeFunnet.setErrorSource(FaultElementetFinnesIkke.getErrorSource()); // move (executionOrder=2)
        FaultNpPersonIkkeFunnet.setErrorType(FaultElementetFinnesIkke.getErrorType()); // move (executionOrder=3)
        FaultNpPersonIkkeFunnet.setDateTimeStamp(FaultElementetFinnesIkke.getDateTimeStamp()); // move (executionOrder=4)
        FaultNpPersonIkkeFunnet.setRootCause(FaultElementetFinnesIkke.getRootCause()); // move (executionOrder=5)
    }

    public static void GBOTjenestepensjonTOASBONpHarTjenestepensjonsforholdResponse(nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon GBOTjenestepensjon, ASBONpHarTjenestepensjonsforholdResponse ASBONpHarTjenestepensjonsforholdResponse) {
    {
        String GBOTjenestepensjon_tjenestepensjonForholdene = GBOTjenestepensjon.getTjenestepensjonForholdene(); // custom.input.forEach (executionOrder=1)
        String ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold = null; // custom.output declaration (executionOrder=1)
        // The specific type of variable GBOTjenestepensjon_tjenestepensjonForholdene is java.util.List
        // The specific type of variable ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold is java.lang.Boolean
        List tplist = (List)GBOTjenestepensjon_tjenestepensjonForholdene;
        
        if (tplist != null && !tplist.isEmpty())
        	ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold = new Boolean(true);
        else 
        	ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold = new Boolean(false);
        
        ASBONpHarTjenestepensjonsforholdResponse.setHarTjenestepensjonsforhold(ASBONpHarTjenestepensjonsforholdResponse_harTjenestepensjonsforhold); // custom.output assignment (executionOrder=1)

    }
    }

}
