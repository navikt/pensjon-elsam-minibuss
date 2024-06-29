package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1;

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.HentTPForholdListeRequestInt;
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt;
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdFinnTjenestepensjonsforholdRequestInt;
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdTjenestepensjonRequestInt;
import no.nav.elsam.registreretpforhold.v0_1.FaultGenerisk;
import no.nav.elsam.registreretpforhold.v0_1.FaultTjenestepensjonForholdIkkeFunnet;
import no.nav.elsam.registreretpforhold.v0_1.HentTPForholdListeResp;
import no.nav.elsam.registreretpforhold.v0_1.TPForhold;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;


public class Mapper {
    public static void FaultElementetErUgyldigTOFaultTjenestepensjonForholdIkkeFunnet(nav_lib_frg.no.nav.lib.frg.fault.FaultElementetErUgyldig FaultElementetErUgyldig, FaultTjenestepensjonForholdIkkeFunnet FaultTjenestepensjonForholdIkkeFunnet) throws DatatypeConfigurationException {
        FaultTjenestepensjonForholdIkkeFunnet.setErrorMessage(FaultElementetErUgyldig.getErrorMessage()); // move (executionOrder=1)
        FaultTjenestepensjonForholdIkkeFunnet.setErrorSource(FaultElementetErUgyldig.getErrorSource()); // move (executionOrder=2)
        FaultTjenestepensjonForholdIkkeFunnet.setRootCause(FaultElementetErUgyldig.getRootCause()); // move (executionOrder=3)
        FaultTjenestepensjonForholdIkkeFunnet.setDateTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(FaultElementetErUgyldig.getDateTimeStamp())); // move (executionOrder=4)
    }

    public static void FaultElementetFinnesIkkeTOFaultTjenestepensjonForholdIkkeFunnet(nav_lib_frg.no.nav.lib.frg.fault.FaultElementetFinnesIkke FaultElementetFinnesIkke, FaultTjenestepensjonForholdIkkeFunnet FaultTjenestepensjonForholdIkkeFunnet) throws DatatypeConfigurationException {
        FaultTjenestepensjonForholdIkkeFunnet.setErrorMessage(FaultElementetFinnesIkke.getErrorMessage()); // move (executionOrder=1)
        FaultTjenestepensjonForholdIkkeFunnet.setErrorSource(FaultElementetFinnesIkke.getErrorSource()); // move (executionOrder=2)
        FaultTjenestepensjonForholdIkkeFunnet.setRootCause(FaultElementetFinnesIkke.getRootCause()); // move (executionOrder=3)
        FaultTjenestepensjonForholdIkkeFunnet.setDateTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(FaultElementetFinnesIkke.getDateTimeStamp())); // move (executionOrder=4)
    }

    public static void FaultTomDatoForanFomDatoTOFaultGenerisk(nav_lib_frg.no.nav.lib.frg.fault.FaultTomDatoForanFomDato FaultTomDatoForanFomDato, FaultGenerisk FaultGenerisk) {
        FaultGenerisk.setErrorDescription(FaultTomDatoForanFomDato.getErrorMessage()); // move (executionOrder=1)
        FaultGenerisk.setErrorCode("InternalError"); // set (executionOrder=2)
    }

    public static void GBOTjenestepensjonForholdTOTPForhold(nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjonForhold GBOTjenestepensjonForhold, TPForhold TPForhold) {
        TPForhold.setTpnr(GBOTjenestepensjonForhold.getTpnr()); // move (executionOrder=1)
        TPForhold.setTpnavn(GBOTjenestepensjonForhold.getNavn()); // move (executionOrder=2)
    }

    public static void GBOTjenestepensjonTOHentTPForholdListeResp(nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon GBOTjenestepensjon, HentTPForholdListeResp HentTPForholdListeResponse) {
        GBOTjenestepensjonForholdTOTPForhold(GBOTjenestepensjon.getTjenestepensjonForholdene(),HentTPForholdListeResponse.getTjenestepensjonForholdene()); // submap (executionOrder=1)
    }

    public static void HentTPForholdListeRequestIntTOGBOFinnTjenestepensjonsforholdRequest(HentTPForholdListeRequestInt HentTPForholdListeRequestInt, nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnTjenestepensjonsforholdRequest GBOFinnTjenestepensjonsforholdRequest) {
        GBOFinnTjenestepensjonsforholdRequest.setFnr(HentTPForholdListeRequestInt.getExtRequest().getFnr()); // move (executionOrder=1)
        GBOFinnTjenestepensjonsforholdRequest.setHentSamhandlerInfo("true"); // set (executionOrder=2)
    }

    public static void OpprettTPForholdRequestIntTOGBOTjenestepensjon(OpprettTPForholdRequestInt OpprettTPForholdRequestInt, nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon GBOTjenestepensjon) {
        GBOTjenestepensjon.setFnr(OpprettTPForholdRequestInt.getExtRequest().getFnr()); // move (executionOrder=1)
        OpprettTPForholdRequestIntTOGBOTjenestepensjonForhold(OpprettTPForholdRequestInt,GBOTjenestepensjon.getTjenestepensjonForholdene()); // submap (executionOrder=2)
    }

    public static void OpprettTPForholdRequestIntTOGBOTjenestepensjonForhold(OpprettTPForholdRequestInt OpprettTPForholdRequestInt, nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjonForhold GBOTjenestepensjonForhold) {
        GBOTjenestepensjonForhold.setTssEksternId(OpprettTPForholdRequestInt.getEksternTSSId()); // move (executionOrder=1)
        GBOTjenestepensjonForhold.setHarUtlandPensjon("false"); // set (executionOrder=2)
    {
        String OpprettTPForholdRequestInt_extRequest_samtykke = OpprettTPForholdRequestInt.getExtRequest().getSamtykke(); // custom.input.forEach (executionOrder=3)
        String GBOTjenestepensjonForhold_samtykkeSimuleringKode = null; // custom.output declaration (executionOrder=3)
        // The specific type of variable OpprettTPForholdRequestInt_extRequest_samtykke_value is java.lang.Boolean
        // The specific type of variable GBOTjenestepensjonForhold_samtykkeSimuleringKode is java.lang.String
        GBOTjenestepensjonForhold_samtykkeSimuleringKode = (OpprettTPForholdRequestInt_extRequest_samtykke != null && ((Boolean) OpprettTPForholdRequestInt_extRequest_samtykke).booleanValue()) ? "J" : "N";
        
        GBOTjenestepensjonForhold.setSamtykkeSimuleringKode(GBOTjenestepensjonForhold_samtykkeSimuleringKode); // custom.output assignment (executionOrder=3)

    }
        GBOTjenestepensjonForhold.setHarSimulering("false"); // set (executionOrder=4)
    }

    public static void SlettTPForholdRequestIntTOGBOFinnTjenestepensjonsforholdRequest(SlettTPForholdFinnTjenestepensjonsforholdRequestInt SlettTPForholdRequestInt, nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnTjenestepensjonsforholdRequest GBOFinnTjenestepensjonsforholdRequest) {
        GBOFinnTjenestepensjonsforholdRequest.setTssEksternId(SlettTPForholdRequestInt.getEksternTSSId()); // move (executionOrder=1)
        GBOFinnTjenestepensjonsforholdRequest.setFnr(SlettTPForholdRequestInt.getExtRequest().getFnr()); // move (executionOrder=2)
        GBOFinnTjenestepensjonsforholdRequest.setHentSamhandlerInfo("false"); // set (executionOrder=3)
    }

    public static void SlettTPForholdRequestIntTOGBOTjenestepensjonForhold(SlettTPForholdTjenestepensjonRequestInt SlettTPForholdTjenestepensjonRequestInt, nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjonForhold GBOTjenestepensjonForhold) {
        GBOTjenestepensjonForhold.setForholdId(SlettTPForholdTjenestepensjonRequestInt.getForholdId()); // move (executionOrder=1)
    }

}
