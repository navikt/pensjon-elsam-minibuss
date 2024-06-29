package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import no.nav.java.usage.logging.UsageLogging;
import no.stelvio.common.bus.util.ErrorHelperUtil;

import com.ibm.websphere.sca.Service;
import com.ibm.websphere.sca.ServiceBusinessException;
import com.ibm.websphere.sca.ServiceManager;
import com.ibm.websphere.sca.ServiceRuntimeException;
import com.ibm.websphere.sca.ServiceUnavailableException;
import com.ibm.websphere.sca.Ticket;
import com.ibm.websphere.sca.sdo.DataFactory;

import commonj.sdo.DataObject;

public class authorizeAndOrchestrateImpl {

    private final static String NAMESPACE = "http://nav.no/elsam/registreretpforhold/V0_1";

    private final static String INT_ASBO_NAMESPACE = "http://nav-cons-elsam-tptilb-registreretpforhold/no/nav/asbo";

    private final static String MODULENAME = "nav-cons-elsam-tptilb-registreretpforhold";

    //MinSide error-codes
    private final static String SERVICEUNAVAILABLE = "ServiceUnavailable";

    private final static String INTERNALERROR = "InternalError";

    /**
     * Default constructor.
     */
    public authorizeAndOrchestrateImpl() {
        super();
    }

    /**
     * Return a reference to the component service instance for this implementation
     * class.  This method should be used when passing this service to a partner reference
     * or if you want to invoke this component service asynchronously.    
     *
     * @generated (com.ibm.wbit.java)
     */
    @SuppressWarnings("unused")
	private Object getMyService() {
        return (Object) ServiceManager.INSTANCE.locateService("self");
    }

    /**
     * This method is used to locate the service for the reference
     * named "SamhandlerPartner".  This will return an instance of 
     * {@link com.ibm.websphere.sca.Service}.  This is the dynamic
     * interface which is used to invoke operations on the reference service
     * either synchronously or asynchronously.  You will need to pass the operation
     * name in order to invoke an operation on the service.
     *
     * @generated (com.ibm.wbit.java)
     *
     * @return Service
     */
    public Service locateService_SamhandlerPartner() {
        return (Service) ServiceManager.INSTANCE.locateService("SamhandlerPartner");
    }

    /**
     * This method is used to locate the service for the reference
     * named "RegistrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold".  This will return an instance of 
     * {@link com.ibm.websphere.sca.Service}.  This is the dynamic
     * interface which is used to invoke operations on the reference service
     * either synchronously or asynchronously.  You will need to pass the operation
     * name in order to invoke an operation on the service.
     *
     * @generated (com.ibm.wbit.java)
     *
     * @return Service
     */
    public Service locateService_RegistrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold() {
        return (Service) ServiceManager.INSTANCE.locateService("RegistrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold");
    }

    /**
     * This method is used to locate the service for the reference
     * named "RegistrereTPForholdV0_1IntPartner_Tjenestepensjon".  This will return an instance of 
     * {@link com.ibm.websphere.sca.Service}.  This is the dynamic
     * interface which is used to invoke operations on the reference service
     * either synchronously or asynchronously.  You will need to pass the operation
     * name in order to invoke an operation on the service.
     *
     * @generated (com.ibm.wbit.java)
     *
     * @return Service
     */
    public Service locateService_RegistrereTPForholdV0_1IntPartner_Tjenestepensjon() {
        return (Service) ServiceManager.INSTANCE.locateService("RegistrereTPForholdV0_1IntPartner_Tjenestepensjon");
    }

    /**
     * Method generated to support implemention of operation
     * "hentTPForholdListe" defined for WSDL port type named
     * "interface.RegistrereTPForholdV0_1".
     * 
     * The presence of commonj.sdo.DataObject as the return type and/or as a
     * parameter type conveys that its a complex type. Please refer to the WSDL
     * Definition for more information on the type of input, output and
     * fault(s).
     */
    public DataObject hentTPForholdListe(DataObject hentTPForholdListeRequest) {
		UsageLogging.loggUsage();
        // Call entity service
        DataObject response = null;
        try {
            DataObject intRequest = DataFactory.INSTANCE.create(INT_ASBO_NAMESPACE, "HentTPForholdListeRequestInt");
            intRequest.setDataObject("extRequest", hentTPForholdListeRequest);
            response = (DataObject) locateService_RegistrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold().invoke("hentTPForholdListeInt", intRequest);
        } catch (ServiceUnavailableException e) {
            throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(), ErrorHelperUtil.getRootCause(e).toString());
        } catch (ServiceRuntimeException e) {
            throw createTechnicalFault(INTERNALERROR, e.getMessage(), ErrorHelperUtil.getRootCause(e).toString());
        }

        if (response != null)
            response = response.getDataObject(0);

        // Ensure that the caller has a TP-forhold to the subject
        boolean ownTPnrExists = false;
        List tpForholdene = response.getList("tjenestepensjonForholdene");
        for (Iterator iter = tpForholdene.iterator(); iter.hasNext();) {
            DataObject tpForhold = (DataObject) iter.next();
            if (tpForhold != null && tpForhold.getString("tpnr") != null && tpForhold.getString("tpnr").equals(hentTPForholdListeRequest.getString("tpnr"))) {
                ownTPnrExists = true;
            }
        }
        if (!ownTPnrExists) {
            throw new ServiceBusinessException(getBusinessFaultBO(MODULENAME, NAMESPACE, "FaultTjenestepensjonForholdIkkeFunnet",
                    "Eget TP-nummer finnes ikke blant registrerte TP-forhold", null));
        }

        return response;
    }

    /**
     * Method generated to support implemention of operation "opprettTPForhold"
     * defined for WSDL port type named "interface.RegistrereTPForholdV0_1".
     * 
     * The presence of commonj.sdo.DataObject as the return type and/or as a
     * parameter type conveys that its a complex type. Please refer to the WSDL
     * Definition for more information on the type of input, output and
     * fault(s).
     */
    public void opprettTPForhold(DataObject opprettTPForholdRequest) {
		UsageLogging.loggUsage();
        // Map TP-nummer to tssEksternId
        String eksternTSSId = mapTPnrToTSSEksternId(opprettTPForholdRequest.getString("tpnr"));

        // Copy external interface to internal and set externalTSSId
        DataObject intRequest = DataFactory.INSTANCE.create(INT_ASBO_NAMESPACE, "OpprettTPForholdRequestInt");
        intRequest.setDataObject("extRequest", opprettTPForholdRequest);
        intRequest.setString("eksternTSSId", eksternTSSId);

        // Call entity service
        try {
            locateService_RegistrereTPForholdV0_1IntPartner_Tjenestepensjon().invoke("opprettTPForholdInt", intRequest);
        } catch (ServiceUnavailableException e) {
            throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(), ErrorHelperUtil.getRootCause(e).toString());
        } catch (ServiceRuntimeException e) {
            throw createTechnicalFault(INTERNALERROR, e.getMessage(), ErrorHelperUtil.getRootCause(e).toString());
        } catch (ServiceBusinessException e) {
            DataObject businessException = (DataObject) e.getData();
            if (businessException.getType().getURI().equals("http://nav-lib-frg/no/nav/lib/frg/fault") &&
                    businessException.getType().getName().equals("FaultElementetErDuplikat")) {
                // Do nothing - duplicate creates are not reported as a fault to external consumers
            } else {
                // Rethrow all other business exceptions
                throw e;
            }
        }
    }

    /**
     * Method generated to support implemention of operation "slettTPForhold"
     * defined for WSDL port type named "interface.RegistrereTPForholdV0_1".
     * 
     * The presence of commonj.sdo.DataObject as the return type and/or as a
     * parameter type conveys that its a complex type. Please refer to the WSDL
     * Definition for more information on the type of input, output and
     * fault(s).
     */
    public void slettTPForhold(DataObject slettTPForholdRequest) {
		UsageLogging.loggUsage();
        // Map TP-nummer to tssEksternId
        String eksternTSSId = mapTPnrToTSSEksternId(slettTPForholdRequest.getString("tpnr"));

        // Copy external interface to internal and set externalTSSId
        DataObject intRequest = DataFactory.INSTANCE.create(INT_ASBO_NAMESPACE, "SlettTPForholdFinnTjenestepensjonsforholdRequestInt");
        intRequest.setDataObject("extRequest", slettTPForholdRequest);
        intRequest.setString("eksternTSSId", eksternTSSId);

        // Retrieve TP-forhold
        DataObject response = null;
        try {
            response = (DataObject) locateService_RegistrereTPForholdV0_1IntPartner_FinnTjenestepensjonsforhold().invoke(
                    "slettTPForholdFinnTjenestepensjonsforholdInt", intRequest);
        } catch (ServiceUnavailableException e) {
            throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(), ErrorHelperUtil.getRootCause(e).toString());
        } catch (ServiceRuntimeException e) {
            throw createTechnicalFault(INTERNALERROR, e.getMessage(), ErrorHelperUtil.getRootCause(e).toString());
        }
        List tpForholdene = response.getDataObject(0).getList("tjenestepensjonForholdene");

        // Validate result
        if (tpForholdene.size() < 1) {
            throw new ServiceBusinessException(getBusinessFaultBO(MODULENAME, NAMESPACE, "FaultTjenestepensjonForholdIkkeFunnet",
                    "TP-forholdet finnes ikke i registeret", null));
        }
        if (tpForholdene.size() > 1) {
            throw createTechnicalFault(INTERNALERROR, "Dublett funnet, inkonsistens i registeret", "Teknisk feil");
        }
        DataObject tpForholdet = (DataObject) tpForholdene.get(0);

        // Disallow cascade delete
        List tjenestepensjonYtelseListe = tpForholdet.getList("tjenestepensjonYtelseListe");
        if (tjenestepensjonYtelseListe.size() > 0) {
            throw new ServiceBusinessException(getBusinessFaultBO(MODULENAME, NAMESPACE, "FaultKanIkkeSlettes",
                    "TP-forholdet kan ikke slettes fordi det er registrert en eller flere TP-ytelser på forholdet.", null));
        }

        // Create new data object for delete service
        intRequest = DataFactory.INSTANCE.create(INT_ASBO_NAMESPACE, "SlettTPForholdTjenestepensjonRequestInt");
        intRequest.setString("forholdId", tpForholdet.getString("forholdId"));

        try {
            // Delete TP-forhold
            locateService_RegistrereTPForholdV0_1IntPartner_Tjenestepensjon().invoke("slettTPForholdTjenestepensjonInt", intRequest);
        } catch (ServiceUnavailableException e) {
            throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(), ErrorHelperUtil.getRootCause(e).toString());
        } catch (ServiceRuntimeException e) {
            throw createTechnicalFault(INTERNALERROR, e.getMessage(), ErrorHelperUtil.getRootCause(e).toString());
        }
    }

    // ****************************
    // 		Utility methods
    // ****************************

    /**
     * Map from TP-nummer to tssEksternId by searching TSS for samhandler
     * with samhandlerType TEPE, idType TPNR and the specified TP-nummer
     * Should only return one tssEksternId 
     * 
     * @param tpNr TP-number (4 digits)
     *            
     * @return eksternTSSId (key to Samhandler)
     */
    private String mapTPnrToTSSEksternId(String tpNr) {
        // Build request object for samhandler
        DataObject samhandlerRequest = DataFactory.INSTANCE.create("http://nav-lib-frg/no/nav/lib/frg/gbo", "GBOFinnSamhandlerRequest");
        samhandlerRequest.setString("offentligId", tpNr);
        samhandlerRequest.setString("idType", "TPNR");
        samhandlerRequest.setString("samhandlerType", "TEPE");

        DataObject samhandlerResponse = null;
        try {
            samhandlerResponse = (DataObject) locateService_SamhandlerPartner().invoke("finnSamhandler", samhandlerRequest);
        } catch (ServiceUnavailableException e) {
            throw createTechnicalFault(SERVICEUNAVAILABLE, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "FinnSamhandler ikke tilgjengelig");
        } catch (ServiceRuntimeException e) {
            throw createTechnicalFault(INTERNALERROR, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Teknisk feil ved oppslag av samhandler");
        } catch (ServiceBusinessException e) {
            throw createTechnicalFault(INTERNALERROR, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Feil ved oppslag av samhandler");
        }

        if (samhandlerResponse == null) {
            throw createTechnicalFault(INTERNALERROR, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Tomt svar ved oppslag av samhandler");
        }
        samhandlerResponse = (DataObject) samhandlerResponse.get(0);
        if (samhandlerResponse == null) {
            throw createTechnicalFault(INTERNALERROR, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Uventet format på svarmelding ved oppslag av samhandler");
        }
        List samhandlere = samhandlerResponse.getList("samhandlere");
        if (samhandlere.size() > 1) {
            //Got more than 1 samhandler in the response. Should receive only 1.
            throw createTechnicalFault(INTERNALERROR, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Ikke entydig treff på TP-nr");
        } else if (samhandlere.size() == 1) {
            DataObject samhandler = (DataObject) samhandlere.get(0);

            //Store idTSSEkstern in class variable for later use in other methods
            List avdelinger = samhandler.getList("avdelinger");
            if (avdelinger == null) {
                throw createTechnicalFault(INTERNALERROR, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Fant ingen avdelinger på samhandleren");
            }
            for (Iterator iter = avdelinger.iterator(); iter.hasNext();) {
                DataObject avdeling = (DataObject) iter.next();
                if (avdeling != null) {
                    if (avdeling.getString("avdelingsnr").equals("01")) {
                        return avdeling.getString("idTSSEkstern");
                    }
                }
            }
            throw createTechnicalFault(INTERNALERROR, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "Ikke registrert en avdeling 01 på samhandleren");
        } else {
            // Samhandler list is null-object.
            throw createTechnicalFault(INTERNALERROR, "Det oppstod en feil under mapping fra TP-nummer til intern ID", "TP-nummeret er ikke registrert");
        }
    }

    private ServiceBusinessException createTechnicalFault(String errorCode, String errorDescription, String errorDetail) {
        List<String> errorDetails = new ArrayList<String>();
        errorDetails.add(errorDetail);
        return createTechnicalFault(errorCode, errorDescription, errorDetails);
    }

    private ServiceBusinessException createTechnicalFault(String errorCode, String errorDescription, List errorDetails) {
        DataObject faultBO = DataFactory.INSTANCE.create(NAMESPACE, "FaultGenerisk");
        faultBO.setString("errorCode", errorCode);
        faultBO.setString("errorDescription", errorDescription);
        faultBO.setList("errorDetails", errorDetails);
        return new ServiceBusinessException(faultBO);
    }

    /**
     * 
     * Method used to create a fault object when a ServiceRuntimeException does not exist. 
     * 
     * @param module				the name of the module that caught the sre
     * @param faultBONamespace		the namespace of the fault object 
     * @param faultBOName			the name of the fault object 
     * @param errorMessage			enables the user to provide an errorMessage as there is no SRE available
     * @param rootCause				enables the user to provide an rootCause as there is no SRE available
     * @return						the fault business object created
     */
    private static DataObject getBusinessFaultBO(String module, String faultBONamespace, String faultBOName, String errorMessage, String rootCause) {
        DataObject faultBo = DataFactory.INSTANCE.create(faultBONamespace, faultBOName);
        faultBo.setString("errorMessage", errorMessage);
        faultBo.setString("errorSource", ErrorHelperUtil.getSCAContext(module));
        if (rootCause != null) {
            faultBo.setString("rootCause", rootCause);
        }
        faultBo.setDate("dateTimeStamp", new Date());

        return faultBo;
    }

    // ****************************
    // 	  End of utility methods
    // ****************************

    /**
     * Method generated to support the async implementation using callback for
     * the operation (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler#hentSamhandler(DataObject
     * aDataObject)) of java interface (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler)
     * 
     * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#hentSamhandler(DataObject
     *      aDataObject)
     */
    public void onHentSamhandlerResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

    /**
     * Method generated to support the async implementation using callback for
     * the operation (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler#lagreSamhandler(DataObject
     * aDataObject)) of java interface (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler)
     * 
     * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#lagreSamhandler(DataObject
     *      aDataObject)
     */
    public void onLagreSamhandlerResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

    /**
     * Method generated to support the async implementation using callback for
     * the operation (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler#opprettSamhandler(DataObject
     * aDataObject)) of java interface (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler)
     * 
     * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#opprettSamhandler(DataObject
     *      aDataObject)
     */
    public void onOpprettSamhandlerResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

    /**
     * Method generated to support the async implementation using callback for
     * the operation (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler#finnSamhandler(DataObject
     * aDataObject)) of java interface (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler)
     * 
     * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#finnSamhandler(DataObject
     *      aDataObject)
     */
    public void onFinnSamhandlerResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

    /**
     * Method generated to support the async implementation using callback for
     * the operation (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler#hentEksternTilkoblingListe(DataObject
     * aDataObject)) of java interface (@link
     * frg.lib.nav.no.nav.lib.frg.inf.Samhandler)
     * 
     * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#hentEksternTilkoblingListe(DataObject
     *      aDataObject)
     */
    public void onHentEksternTilkoblingListeResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

    /**
     * Method generated to support the async implementation using callback for
     * the operation
     * "interface.RegistrereTPForholdV0_1Int#slettTPForholdFinnTjenestepensjonsforholdInt(DataObject
     * slettTPForholdRequestInt)" of wsdl interface
     * "interface.RegistrereTPForholdV0_1Int"
     */
    public void onSlettTPForholdFinnTjenestepensjonsforholdIntResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

    /**
     * Method generated to support the async implementation using callback for
     * the operation
     * "interface.RegistrereTPForholdV0_1Int#hentTPForholdListeInt(DataObject
     * hentTPForholdListeRequestInt)" of wsdl interface
     * "interface.RegistrereTPForholdV0_1Int"
     */
    public void onHentTPForholdListeIntResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

    /**
     * Method generated to support the async implementation using callback for
     * the operation (@link
     * no.nav.elsam.registreretpforhold.v._01.RegistrereTPForholdV0_1#hentTPForholdListe(DataObject
     * aDataObject)) of java interface (@link
     * no.nav.elsam.registreretpforhold.v._01.RegistrereTPForholdV0_1)
     * 
     * @see no.nav.elsam.registreretpforhold.v._01.RegistrereTPForholdV0_1#hentTPForholdListe(DataObject
     *      aDataObject)
     */
    public void onHentTPForholdListeResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

    /**
     * Method generated to support the async implementation using callback for
     * the operation
     * "interface.RegistrereTPForholdV0_1Int#slettTPForholdInt(DataObject
     * slettTPForholdRequestInt)" of wsdl interface
     * "interface.RegistrereTPForholdV0_1Int"
     */
    public void onSlettTPForholdIntResponse(Ticket __ticket, DataObject returnValue, Exception exception) {
    }

}
