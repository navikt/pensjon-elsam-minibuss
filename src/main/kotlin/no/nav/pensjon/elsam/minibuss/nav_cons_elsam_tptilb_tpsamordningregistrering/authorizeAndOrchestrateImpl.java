package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering;

import java.util.ArrayList;
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

	private final static String NAMESPACE = "http://nav.no/elsam/tpsamordningregistrering/V0_5";

	private final static String INT_ASBO_NAMESPACE = "http://nav-cons-elsam-tptilb-tpsamordningregistrering/no/nav/asbo";

	private final static String MODULENAME = "nav-cons-elsam-tptilb-tpsamordningregistrering";

	// MinSide error-codes
	private final static String UNKNOWNID = "UnknownId";

	private final static String REQUESTTIMEDOUT = "RequestTimedOut";

	private final static String SERVICEUNAVAILABLE = "ServiceUnavailable";

	private final static String NOTAUTHORIZED = "NotAuthorized";

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
	private Service _SamhandlerPartner = null;

	/**
	 * This method is used to locate the service for the reference named
	 * "SamhandlerPartner". This will return an instance of
	 * {@link com.ibm.websphere.sca.Service}. This is the dynamic interface
	 * which is used to invoke operations on the reference service either
	 * synchronously or asynchronously. You will need to pass the operation name
	 * in order to invoke an operation on the service.
	 * 
	 * @generated (com.ibm.wbit.java)
	 * 
	 * @return Service
	 */
	public Service locateService_SamhandlerPartner() {
		return (Service) ServiceManager.INSTANCE
				.locateService("SamhandlerPartner");
	}

	/**
	 * This method is used to locate the service for the reference
	 * named "TPSamordningRegistreringIntPartner".  This will return an instance of 
	 * {@link com.ibm.websphere.sca.Service}.  This is the dynamic
	 * interface which is used to invoke operations on the reference service
	 * either synchronously or asynchronously.  You will need to pass the operation
	 * name in order to invoke an operation on the service.
	 *
	 * @generated (com.ibm.wbit.java)
	 *
	 * @return Service
	 */
	private Service _TPSamordningRegistreringIntPartner = null;

	/**
	 * This method is used to locate the service for the reference named
	 * "TPSamordningRegistreringIntPartner". This will return an instance of
	 * {@link com.ibm.websphere.sca.Service}. This is the dynamic interface
	 * which is used to invoke operations on the reference service either
	 * synchronously or asynchronously. You will need to pass the operation name
	 * in order to invoke an operation on the service.
	 * 
	 * @generated (com.ibm.wbit.java)
	 * 
	 * @return Service
	 */
	public Service locateService_TPSamordningRegistreringIntPartner() {
		return (Service) ServiceManager.INSTANCE
				.locateService("TPSamordningRegistreringIntPartner");
	}

	/**
	 * Method generated to support implemention of operation "lagreTPYtelse"
	 * defined for WSDL port type named "interface.TPSamordningRegistrering".
	 * 
	 * The presence of commonj.sdo.DataObject as the return type and/or as a
	 * parameter type conveys that its a complex type. Please refer to the WSDL
	 * Definition for more information on the type of input, output and
	 * fault(s).
	 */
	public DataObject lagreTPYtelse(DataObject lagreTPYtelseReq) {
		UsageLogging.loggUsage();
		// Map TP-nummer to tssEksternId
		String eksternTSSId = mapTPnrToTSSEksternId(lagreTPYtelseReq
				.getString("tpnr"));

		// Copy external interface to internal and set externalTSSId
		DataObject intRequest = DataFactory.INSTANCE.create(INT_ASBO_NAMESPACE,
				"LagreTPYtelseReqInt");
		intRequest.setDataObject("extRequest", lagreTPYtelseReq);
		intRequest.setString("tssEksternId", eksternTSSId);

		// Call entity service
		try {
			return (DataObject) ((DataObject) locateService_TPSamordningRegistreringIntPartner()
					.invoke("lagreTPYtelseInt", intRequest)).get(0);
		} catch (ServiceUnavailableException e) {
			throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		} catch (ServiceRuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		}
	}

	/**
	 * Method generated to support implemention of operation "slettTPYtelse"
	 * defined for WSDL port type named "interface.TPSamordningRegistrering".
	 * 
	 * The presence of commonj.sdo.DataObject as the return type and/or as a
	 * parameter type conveys that its a complex type. Please refer to the WSDL
	 * Definition for more information on the type of input, output and
	 * fault(s).
	 */
	public void slettTPYtelse(DataObject slettTPYtelseReq) {
		UsageLogging.loggUsage();
		// Map TP-nummer to tssEksternId
		String eksternTSSId = mapTPnrToTSSEksternId(slettTPYtelseReq
				.getString("tpnr"));

		// Copy external interface to internal and set externalTSSId
		DataObject intRequest = DataFactory.INSTANCE.create(INT_ASBO_NAMESPACE,
				"SlettTPYtelseReqInt");
		intRequest.setDataObject("extRequest", slettTPYtelseReq);
		intRequest.setString("tssEksternId", eksternTSSId);

		// Call entity service
		try {
			locateService_TPSamordningRegistreringIntPartner().invoke(
					"slettTPYtelseInt", intRequest);
		} catch (ServiceUnavailableException e) {
			throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		} catch (ServiceRuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		}
	}

	/**
	 * Method generated to support implemention of operation
	 * "hentSamordningsdata" defined for WSDL port type named
	 * "interface.TPSamordningRegistrering".
	 * 
	 * The presence of commonj.sdo.DataObject as the return type and/or as a
	 * parameter type conveys that its a complex type. Please refer to the WSDL
	 * Definition for more information on the type of input, output and
	 * fault(s).
	 */
	public DataObject hentSamordningsdata(DataObject hentSamordningsdataReq) {
		UsageLogging.loggUsage();
		// Map TP-nummer to tssEksternId
		String eksternTSSId = mapTPnrToTSSEksternId(hentSamordningsdataReq
				.getString("tpnr"));

		// Copy external interface to internal and set externalTSSId
		DataObject intRequest = DataFactory.INSTANCE.create(INT_ASBO_NAMESPACE,
				"HentSamordningsdataReqInt");
		intRequest.setDataObject("extRequest", hentSamordningsdataReq);
		intRequest.setString("tssEksternId", eksternTSSId);

		// Call entity service
		try {
			return ((DataObject) locateService_TPSamordningRegistreringIntPartner()
					.invoke("hentSamordningsdataInt", intRequest))
					.getDataObject(0);
		} catch (ServiceUnavailableException e) {
			throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		} catch (ServiceRuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		}
	}

	/**
	 * Method generated to support implemention of operation
	 * "opprettRefusjonskrav" defined for WSDL port type named
	 * "interface.TPSamordningRegistrering".
	 * 
	 * The presence of commonj.sdo.DataObject as the return type and/or as a
	 * parameter type conveys that its a complex type. Please refer to the WSDL
	 * Definition for more information on the type of input, output and
	 * fault(s).
	 */
	public void opprettRefusjonskrav(DataObject opprettRefusjonskravReq) {
		UsageLogging.loggUsage();
		// Map TP-nummer to tssEksternId
		String eksternTSSId = mapTPnrToTSSEksternId(opprettRefusjonskravReq
				.getString("tpnr"));

		// Copy external interface to internal and set externalTSSId
		DataObject intRequest = DataFactory.INSTANCE.create(INT_ASBO_NAMESPACE,
				"OpprettRefusjonskravReqInt");
		intRequest.setDataObject("extRequest", opprettRefusjonskravReq);
		intRequest.setString("tssEksternId", eksternTSSId);

		// Call entity service
		try {
			locateService_TPSamordningRegistreringIntPartner().invoke(
					"opprettRefusjonskravInt", intRequest);
		} catch (ServiceUnavailableException e) {
			throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		} catch (ServiceRuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		}
	}

	// ****************************
	// Utility methods
	// ****************************

	/**
	 * Map from TP-nummer to tssEksternId by searching TSS for samhandler with
	 * samhandlerType TEPE, idType TPNR and the specified TP-nummer Should only
	 * return one tssEksternId
	 * 
	 * @param tpNr
	 *            TP-number (4 digits)
	 * 
	 * @return eksternTSSId (key to Samhandler)
	 */
	private String mapTPnrToTSSEksternId(String tpNr) {
		// Build request object for samhandler
		DataObject samhandlerRequest = DataFactory.INSTANCE.create(
				"http://nav-lib-frg/no/nav/lib/frg/gbo",
				"GBOFinnSamhandlerRequest");
		samhandlerRequest.setString("offentligId", tpNr);
		samhandlerRequest.setString("idType", "TPNR");
		samhandlerRequest.setString("samhandlerType", "TEPE");

		DataObject samhandlerResponse = null;
		try {
			samhandlerResponse = (DataObject) locateService_SamhandlerPartner()
					.invoke("finnSamhandler", samhandlerRequest);
		} catch (ServiceUnavailableException e) {
			throw createTechnicalFault(SERVICEUNAVAILABLE, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		} catch (ServiceRuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(),
					ErrorHelperUtil.getRootCause(e).toString());
		} catch (ServiceBusinessException e) {
			throw createTechnicalFault(
					INTERNALERROR,
					"Det oppstod en feil under mapping fra TP-nummer til intern ID. Feil ved oppslag av samhandler",
					ErrorHelperUtil.getRootCause(e).toString());
		}

		if (samhandlerResponse == null) {
			throw createTechnicalFault(
					INTERNALERROR,
					"Det oppstod en feil under mapping fra TP-nummer til intern ID",
					"Tomt svar ved oppslag av samhandler");
		}
		samhandlerResponse = (DataObject) samhandlerResponse.get(0);
		if (samhandlerResponse == null) {
			throw createTechnicalFault(
					INTERNALERROR,
					"Det oppstod en feil under mapping fra TP-nummer til intern ID",
					"Uventet format på svarmelding ved oppslag av samhandler");
		}
		List samhandlere = samhandlerResponse.getList("samhandlere");
		if (samhandlere.size() > 1) {
			// Got more than 1 samhandler in the response. Should receive only
			// 1.
			throw createTechnicalFault(
					INTERNALERROR,
					"Det oppstod en feil under mapping fra TP-nummer til intern ID",
					"Ikke entydig treff på TP-nr");
		} else if (samhandlere.size() == 1) {
			DataObject samhandler = (DataObject) samhandlere.get(0);

			// Store idTSSEkstern in class variable for later use in other
			// methods
			List avdelinger = samhandler.getList("avdelinger");
			if (avdelinger == null) {
				throw createTechnicalFault(
						INTERNALERROR,
						"Det oppstod en feil under mapping fra TP-nummer til intern ID",
						"Fant ingen avdelinger på samhandleren");
			}
			for (Iterator iter = avdelinger.iterator(); iter.hasNext();) {
				DataObject avdeling = (DataObject) iter.next();
				if (avdeling != null) {
					if (avdeling.getString("avdelingsnr").equals("01")) {
						return avdeling.getString("idTSSEkstern");
					}
				}
			}
			throw createTechnicalFault(
					INTERNALERROR,
					"Det oppstod en feil under mapping fra TP-nummer til intern ID",
					"Ikke registrert en avdeling 01 på samhandleren");
		} else {
			// Samhandler list is null-object.
			throw createTechnicalFault(
					INTERNALERROR,
					"Det oppstod en feil under mapping fra TP-nummer til intern ID",
					"TP-nummeret er ikke registrert");
		}
	}

	private ServiceBusinessException createTechnicalFault(String errorCode,
			String errorDescription, String errorDetail) {
		List errorDetails = new ArrayList();
		errorDetails.add(errorDetail);
		return createTechnicalFault(errorCode, errorDescription, errorDetails);
	}

	private ServiceBusinessException createTechnicalFault(String errorCode,
			String errorDescription, List errorDetails) {
		DataObject faultBO = DataFactory.INSTANCE.create(NAMESPACE,
				"FaultGenerisk");
		faultBO.setString("errorCode", errorCode);
		faultBO.setString("errorDescription", errorDescription);
		faultBO.setList("errorDetails", errorDetails);
		return new ServiceBusinessException(faultBO);
	}

	// ****************************
	// End of utility methods
	// ****************************

	/**
	 * Method generated to support the async implementation using callback for
	 * the operation "interface.Samhandler#hentSamhandler(DataObject
	 * hentSamhandlerRequest)" of wsdl interface "interface.Samhandler"
	 */
	public void onHentSamhandlerResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback for
	 * the operation "interface.Samhandler#lagreSamhandler(DataObject
	 * lagreSamhandlerRequest)" of wsdl interface "interface.Samhandler"
	 */
	public void onLagreSamhandlerResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback for
	 * the operation "interface.Samhandler#opprettSamhandler(DataObject
	 * opprettSamhandlerRequest)" of wsdl interface "interface.Samhandler"
	 */
	public void onOpprettSamhandlerResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback for
	 * the operation "interface.Samhandler#finnSamhandler(DataObject
	 * finnSamhandlerRequest)" of wsdl interface "interface.Samhandler"
	 */
	public void onFinnSamhandlerResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback for
	 * the operation "interface.Samhandler#hentEksternTilkoblingListe(DataObject
	 * hentEksternTilkoblingListeRequest)" of wsdl interface
	 * "interface.Samhandler"
	 */
	public void onHentEksternTilkoblingListeResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback for
	 * the operation
	 * "interface.TPSamordningRegistreringInt#lagreTPYtelseInt(DataObject
	 * lagreTPYtelseReqInt)" of wsdl interface
	 * "interface.TPSamordningRegistreringInt"
	 */
	public void onLagreTPYtelseIntResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback for
	 * the operation
	 * "interface.TPSamordningRegistreringInt#hentSamordningsdataInt(DataObject
	 * hentSamordningsdataReqInt)" of wsdl interface
	 * "interface.TPSamordningRegistreringInt"
	 */
	public void onHentSamordningsdataIntResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

}
