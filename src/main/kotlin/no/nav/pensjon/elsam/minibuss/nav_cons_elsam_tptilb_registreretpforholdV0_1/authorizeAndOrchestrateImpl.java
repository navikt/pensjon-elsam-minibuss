package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.HentTPForholdListeRequestInt;
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt;
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdFinnTjenestepensjonsforholdRequestInt;
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdTjenestepensjonRequestInt;
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.inf.*;
import nav_lib_frg.no.nav.lib.frg.gbo.*;
import nav_lib_frg.no.nav.lib.frg.inf.Samhandler;
import no.nav.elsam.registreretpforhold.v0_1.*;
import org.springframework.core.NestedExceptionUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class authorizeAndOrchestrateImpl {
    private final RegistrereTPForholdInt registrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold;
    private final Samhandler SamhandlerPartner;
    private final RegistrereTPForholdInt registrereTPForholdV0_1IntPartner_Tjenestepensjon;

    public authorizeAndOrchestrateImpl(RegistrereTPForholdInt registrereTPForholdV01IntPartnerFinnTjenestepensjonsforhold, Samhandler samhandlerPartner, RegistrereTPForholdInt registrereTPForholdV01IntPartnerTjenestepensjon) {
        registrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold = registrereTPForholdV01IntPartnerFinnTjenestepensjonsforhold;
        SamhandlerPartner = samhandlerPartner;
        registrereTPForholdV0_1IntPartner_Tjenestepensjon = registrereTPForholdV01IntPartnerTjenestepensjon;
    }

    public HentTPForholdListeResp hentTPForholdListe(HentTPForholdListeReq hentTPForholdListeRequest) throws ServiceBusinessException, DatatypeConfigurationException, HentTPForholdListeIntFaultTjenestepensjonForholdIkkeFunnetMsg, HentTPForholdListeIntFaultGeneriskMsg {
        // Call entity service
        HentTPForholdListeResp response;
        try {
            HentTPForholdListeRequestInt intRequest = new HentTPForholdListeRequestInt();
            intRequest.setExtRequest(hentTPForholdListeRequest);
            response = registrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold.hentTPForholdListeInt(intRequest);
        } catch (RuntimeException e) {
            throw createTechnicalFault(e.getMessage(), List.of(NestedExceptionUtils.getMostSpecificCause(e).toString()));
        }

        // Ensure that the caller has a TP-forhold to the subject
        boolean ownTPnrExists = false;
        for (TPForhold tpForhold : response.getTjenestepensjonForholdene()) {
            if (tpForhold != null && tpForhold.getTpnr() != null && tpForhold.getTpnr().equals(hentTPForholdListeRequest.getTpnr())) {
                ownTPnrExists = true;
                break;
            }
        }
        if (!ownTPnrExists) {
            throw new ServiceBusinessException(getFaultTjenestepensjonForholdIkkeFunnet("Eget TP-nummer finnes ikke blant registrerte TP-forhold"));
        }

        return response;
    }

    public void opprettTPForhold(OpprettTPForholdReq opprettTPForholdRequest) throws ServiceBusinessException {
        // Map TP-nummer to tssEksternId
        String eksternTSSId = mapTPnrToTSSEksternId(opprettTPForholdRequest.getTpnr());

        // Copy external interface to internal and set externalTSSId
        OpprettTPForholdRequestInt intRequest = new OpprettTPForholdRequestInt();
        intRequest.setExtRequest(opprettTPForholdRequest);
        intRequest.setEksternTSSId(eksternTSSId);

        // Call entity service
        try {
            registrereTPForholdV0_1IntPartner_Tjenestepensjon.opprettTPForholdInt(intRequest);
        } catch (RuntimeException e) {
            throw createTechnicalFault(e.getMessage(), NestedExceptionUtils.getMostSpecificCause(e).toString());
        } catch (OpprettTPForholdIntFaultElementetErDuplikatMsg e) {
            // Do nothing - duplicate creates are not reported as a fault to external consumers
        }
    }

    public void slettTPForhold(SlettTPForholdReq slettTPForholdRequest) throws ServiceBusinessException, SlettTPForholdFinnTjenestepensjonsforholdIntFaultTjenestepensjonForholdIkkeFunnetIntMsg, SlettTPForholdFinnTjenestepensjonsforholdIntFaultGeneriskMsg, DatatypeConfigurationException, SlettTPForholdTjenestepensjonIntFaultTjenestepensjonForholdIkkeFunnetIntMsg {
        // Map TP-nummer to tssEksternId
        String eksternTSSId = mapTPnrToTSSEksternId(slettTPForholdRequest.getTpnr());

        // Retrieve TP-forhold
        GBOTjenestepensjon response;
        try {
            // Copy external interface to internal and set externalTSSId
            SlettTPForholdFinnTjenestepensjonsforholdRequestInt intRequest = new SlettTPForholdFinnTjenestepensjonsforholdRequestInt();
            intRequest.setExtRequest(slettTPForholdRequest);
            intRequest.setEksternTSSId(eksternTSSId);

            response = registrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold.slettTPForholdFinnTjenestepensjonsforholdInt(intRequest);
        } catch (RuntimeException e) {
            throw createTechnicalFault(e.getMessage(), List.of(NestedExceptionUtils.getMostSpecificCause(e).toString()));
        }
        List<GBOTjenestepensjonForhold> tpForholdene = response.getTjenestepensjonForholdene();

        // Validate result
        if (tpForholdene.isEmpty()) {
            throw new ServiceBusinessException(getFaultTjenestepensjonForholdIkkeFunnet("TP-forholdet finnes ikke i registeret"));
        }
        if (tpForholdene.size() > 1) {
            throw createTechnicalFault("Dublett funnet, inkonsistens i registeret", "Teknisk feil");
        }
        GBOTjenestepensjonForhold tpForholdet = tpForholdene.get(0);

        // Disallow cascade delete
        List<GBOTjenestepensjonYtelse> tjenestepensjonYtelseListe = tpForholdet.getTjenestepensjonYtelseListe();
        if (!tjenestepensjonYtelseListe.isEmpty()) {
            throw new ServiceBusinessException(getFaultKanIkkeSlettes("TP-forholdet kan ikke slettes fordi det er registrert en eller flere TP-ytelser på forholdet."));
        }

        try {
            // Create new data object for delete service
            SlettTPForholdTjenestepensjonRequestInt intRequest = new SlettTPForholdTjenestepensjonRequestInt();
            intRequest.setForholdId(tpForholdet.getForholdId());
            // Delete TP-forhold
            registrereTPForholdV0_1IntPartner_Tjenestepensjon.slettTPForholdTjenestepensjonInt(intRequest);
        } catch (RuntimeException e) {
            throw createTechnicalFault(e.getMessage(), List.of(NestedExceptionUtils.getMostSpecificCause(e).toString()));
        }
    }

    /**
     * Map from TP-nummer to tssEksternId by searching TSS for samhandler
     * with samhandlerType TEPE, idType TPNR and the specified TP-nummer
     * Should only return one tssEksternId 
     * 
     * @param tpNr TP-number (4 digits)
     *            
     * @return eksternTSSId (key to Samhandler)
     */
    private String mapTPnrToTSSEksternId(String tpNr) throws ServiceBusinessException {
        // Build request object for samhandler
        GBOFinnSamhandlerRequest samhandlerRequest = new GBOFinnSamhandlerRequest();
        samhandlerRequest.setOffentligId(tpNr);
        samhandlerRequest.setIdType("TPNR");
        samhandlerRequest.setSamhandlerType("TEPE");

        GBOSamhandlerListe samhandlerResponse;
        try {
            samhandlerResponse = SamhandlerPartner.finnSamhandler(samhandlerRequest);
        } catch (RuntimeException e) {
            throw createTechnicalFault("Det oppstod en feil under mapping fra TP-nummer til intern ID", "Feil ved oppslag av samhandler");
        }

        if (samhandlerResponse == null) {
            throw createTechnicalFault("Det oppstod en feil under mapping fra TP-nummer til intern ID", "Tomt svar ved oppslag av samhandler");
        }
        List<GBOSamhandler> samhandlere = samhandlerResponse.getSamhandlere();
        if (samhandlere.size() > 1) {
            //Got more than 1 samhandler in the response. Should receive only 1.
            throw createTechnicalFault("Det oppstod en feil under mapping fra TP-nummer til intern ID", "Ikke entydig treff på TP-nr");
        } else if (samhandlere.size() == 1) {
            GBOSamhandler samhandler = samhandlere.get(0);

            //Store idTSSEkstern in class variable for later use in other methods
            List<GBOAvdeling> avdelinger = samhandler.getAvdelinger();
            if (avdelinger == null) {
                throw createTechnicalFault("Det oppstod en feil under mapping fra TP-nummer til intern ID", "Fant ingen avdelinger på samhandleren");
            }
            for (GBOAvdeling avdeling : avdelinger) {
                if (avdeling != null) {
                    if (avdeling.getAvdelingsnr().equals("01")) {
                        return avdeling.getIdTSSEkstern();
                    }
                }
            }
            throw createTechnicalFault("Det oppstod en feil under mapping fra TP-nummer til intern ID", "Ikke registrert en avdeling 01 på samhandleren");
        } else {
            // Samhandler list is null-object.
            throw createTechnicalFault("Det oppstod en feil under mapping fra TP-nummer til intern ID", "TP-nummeret er ikke registrert");
        }
    }

    private ServiceBusinessException createTechnicalFault(String errorDescription, String errorDetail) {
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(errorDetail);
        return createTechnicalFault(errorDescription, errorDetails);
    }

    private ServiceBusinessException createTechnicalFault(String errorDescription, List<String> errorDetails) {
        FaultGenerisk faultBO = new FaultGenerisk();
        faultBO.setErrorCode("InternalError");
        faultBO.setErrorDescription(errorDescription);
        faultBO.getErrorDetails().addAll(errorDetails);
        return new ServiceBusinessException(faultBO);
    }

    private static FaultTjenestepensjonForholdIkkeFunnet getFaultTjenestepensjonForholdIkkeFunnet(String errorMessage) throws DatatypeConfigurationException {
        FaultTjenestepensjonForholdIkkeFunnet faultBo = new FaultTjenestepensjonForholdIkkeFunnet();
        faultBo.setErrorMessage(errorMessage);
        faultBo.setErrorSource("nav-cons-elsam-tptilb-registreretpforhold");
        faultBo.setDateTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDateTime.now().toString()));

        return faultBo;
    }

    private static FaultKanIkkeSlettes getFaultKanIkkeSlettes(String errorMessage) throws DatatypeConfigurationException {
        FaultKanIkkeSlettes faultBo = new FaultKanIkkeSlettes();
        faultBo.setErrorMessage(errorMessage);
        faultBo.setErrorSource("nav-cons-elsam-tptilb-registreretpforhold");
        faultBo.setDateTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDateTime.now().toString()));
        return faultBo;
    }
}
