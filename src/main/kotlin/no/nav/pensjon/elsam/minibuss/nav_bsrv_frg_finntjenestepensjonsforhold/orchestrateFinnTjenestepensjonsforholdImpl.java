package no.nav.pensjon.elsam.minibuss.nav_bsrv_frg_finntjenestepensjonsforhold;

import java.util.Iterator;
import java.util.List;

import com.ibm.websphere.sca.ServiceBusinessException;
import com.ibm.websphere.sca.ServiceManager;
import com.ibm.websphere.sca.Ticket;
import com.ibm.websphere.sca.sdo.DataFactory;
import commonj.sdo.DataObject;

import frg.lib.nav.no.nav.lib.frg.inf.Samhandler;
import frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon;

public class orchestrateFinnTjenestepensjonsforholdImpl {
	private final static String GBONAMESPACE = "http://nav-lib-frg/no/nav/lib/frg/gbo";
	
	/**
	 * Default constructor.
	 */
	public orchestrateFinnTjenestepensjonsforholdImpl() {
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
	 * named "TjenestepensjonPartner".  This will return an instance of
	 * {@link Tjenestepensjon}.  If you would like to use this service 
	 * asynchronously then you will need to cast the result
	 * to {@link TjenestepensjonAsync}.
	 *
	 * @generated (com.ibm.wbit.java)
	 *
	 * @return Tjenestepensjon
	 */
	public Tjenestepensjon locateService_TjenestepensjonPartner() {
		return (Tjenestepensjon) ServiceManager.INSTANCE
				.locateService("TjenestepensjonPartner");
	}

	/**
	 * This method is used to locate the service for the reference
	 * named "SamhandlerPartner".  This will return an instance of
	 * {@link Samhandler}.  If you would like to use this service 
	 * asynchronously then you will need to cast the result
	 * to {@link SamhandlerAsync}.
	 *
	 * @generated (com.ibm.wbit.java)
	 *
	 * @return Samhandler
	 */
	public Samhandler locateService_SamhandlerPartner() {
		return (Samhandler) ServiceManager.INSTANCE
				.locateService("SamhandlerPartner");
	}

	/**
	 * Method generated to support implemention of operation "finnTjenestepensjonsforhold" defined for WSDL port type 
	 * named "interface.FinnTjenestepensjonsforhold".
	 * 
	 * The presence of commonj.sdo.DataObject as the return type and/or as a parameter 
	 * type conveys that its a complex type. Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public DataObject finnTjenestepensjonsforhold(DataObject finnTjenestepensjonsforholdRequest) {
		DataObject finnTjenestepensjonsforholdResponse = null;
		if (finnTjenestepensjonsforholdRequest != null) {
			//Error handling is implicit, since the exact same "fault-BO's" (java-exceptions in the run-time)
			//is sent out from the POJO as is received in to it. The exceptions float up to the cons-layer
			//automatically.

			//Check which search-parameter is given, and invoke the corresponding operation
			if (finnTjenestepensjonsforholdRequest.getString("fnr") != null) {
				finnTjenestepensjonsforholdResponse = locateService_TjenestepensjonPartner().hentTjenestepensjonInfo(mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonInfoRequest(finnTjenestepensjonsforholdRequest));
			}
			else if (finnTjenestepensjonsforholdRequest.getString("forholdId") != null) {
				finnTjenestepensjonsforholdResponse = locateService_TjenestepensjonPartner().hentTjenestepensjonForholdYtelse(mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(finnTjenestepensjonsforholdRequest));
			}
			else if (finnTjenestepensjonsforholdRequest.getString("ytelseId") != null) {
				finnTjenestepensjonsforholdResponse = locateService_TjenestepensjonPartner().hentTjenestepensjonForholdYtelse(mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(finnTjenestepensjonsforholdRequest));
			}
			
			//IF hentSamhandlerInfo is true get samhandler-info for all tjenestepensjonForhold's in the response
			if("true".equalsIgnoreCase(finnTjenestepensjonsforholdRequest.getString("hentSamhandlerInfo"))) {
				finnTjenestepensjonsforholdResponse = fillGBOTjenestepensjonWithSamhandlerInfo(finnTjenestepensjonsforholdResponse);
			}

		}
		return finnTjenestepensjonsforholdResponse;

	}
	

	// ******************
	// UTILITY METHODS
	// ******************
	
	private DataObject mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonInfoRequest(DataObject GBOFinnTjenestepensjonsforholdRequest) {
		DataObject GBOHentTjenestepensjonInfoRequest = null;
		
		if (GBOFinnTjenestepensjonsforholdRequest != null) {
			GBOHentTjenestepensjonInfoRequest = DataFactory.INSTANCE.create(GBONAMESPACE, "GBOHentTjenestepensjonInfoRequest");
			GBOHentTjenestepensjonInfoRequest.setString("fnr", GBOFinnTjenestepensjonsforholdRequest.getString("fnr"));
			GBOHentTjenestepensjonInfoRequest.setString("datoFom", GBOFinnTjenestepensjonsforholdRequest.getString("fom"));
			GBOHentTjenestepensjonInfoRequest.setString("datoTom", GBOFinnTjenestepensjonsforholdRequest.getString("tom"));
			GBOHentTjenestepensjonInfoRequest.setString("tssEksternId", GBOFinnTjenestepensjonsforholdRequest.getString("tssEksternId"));
		}
		
		return GBOHentTjenestepensjonInfoRequest;
	}
	
	private DataObject mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(DataObject GBOFinnTjenestepensjonsforholdRequest) {
		DataObject GBOHentTjenestepensjonForholdYtelseRequest = null;
		
		if (GBOFinnTjenestepensjonsforholdRequest != null) {
			GBOHentTjenestepensjonForholdYtelseRequest = DataFactory.INSTANCE.create(GBONAMESPACE, "GBOHentTjenestepensjonForholdYtelseRequest");
			GBOHentTjenestepensjonForholdYtelseRequest.setString("forholdId", GBOFinnTjenestepensjonsforholdRequest.getString("forholdId"));
			GBOHentTjenestepensjonForholdYtelseRequest.setString("ytelseId", GBOFinnTjenestepensjonsforholdRequest.getString("ytelseId"));
		}
		
		return GBOHentTjenestepensjonForholdYtelseRequest;
	}
	
	private DataObject fillGBOTjenestepensjonWithSamhandlerInfo(DataObject tjenestepensjon) {
		if (tjenestepensjon != null) {
			List tjenestepensjonForholdList = (List) tjenestepensjon.getList("tjenestepensjonForholdene");
			
			if (tjenestepensjonForholdList != null) {
				DataObject tjenestepensjonForhold = null;
				DataObject hentSamhandlerRequest = DataFactory.INSTANCE.create(GBONAMESPACE, "GBOHentSamhandlerRequest");
				DataObject hentSamhandlerResponse = null;
				List samhandlerAvdelingList = null;
				DataObject samhandlerAvdeling = null;
				
				//Iterate through received list to fill in samhandlerInfo on each item in the list
				for (Iterator iter = tjenestepensjonForholdList.iterator(); iter.hasNext();) {
					tjenestepensjonForhold = (DataObject) iter.next();
					if (tjenestepensjonForhold != null) {
						//Create request-object for hentSamhandler-call
						hentSamhandlerRequest.setString("idTSSEkstern", tjenestepensjonForhold.getString("tssEksternId"));
						hentSamhandlerRequest.setBoolean("hentDetaljert", true);
						//Call hentSamhandler to get samhandlerInfo
						try {
							hentSamhandlerResponse = locateService_SamhandlerPartner().hentSamhandler(hentSamhandlerRequest);
							if (hentSamhandlerResponse != null) {
															
								//Get alternativId from the response's list alternativeIder where alternativeIdKode is "TPNR"
								DataObject altId = null;
								List altIdList = (List) hentSamhandlerResponse.getList("alternativeIder");
								if (altIdList != null) {
									boolean done = false;
									for (Iterator i = altIdList.iterator() ; i.hasNext()&& !done;) {
										altId = (DataObject) i.next();
										if (altId != null && altId.get("alternativKode") != null && altId.get("alternativKode").equals("TPNR")) {
											tjenestepensjonForhold.setString("tpnr", altId.getString("alternativId"));
											done=true;
										}
									}
								}	
								
								//Get avdeling from the response and read avdelingNavn from avdeling.
								samhandlerAvdelingList = (List) hentSamhandlerResponse.getList("avdelinger");
								if (samhandlerAvdelingList != null) { 
									//The number of entries in this list will always be 1 for the call used here 
									samhandlerAvdeling = (DataObject) samhandlerAvdelingList.get(0);
									if (samhandlerAvdeling != null)
										tjenestepensjonForhold.setString("navn", samhandlerAvdeling.getString("avdelingNavn"));
									    tjenestepensjonForhold.setString("avdelingType",samhandlerAvdeling.getString("avdelingType"));
								}
							}
						} catch (ServiceBusinessException sbe) {
							//hentSamhandler might return FaultSamhandlerIkkeFunnet. This fault 
							//is ignored and processing continuous with next item in the list.
						}
					}
				}
			}
		}
		
		return tjenestepensjon;
	}
	
	// ***********************************
	// ASYNC RESPONSE METHODS, NOT USED
	// **********************************
	
	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#hentStillingsprosentListe(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#hentStillingsprosentListe(DataObject aDataObject)	
	 */
	public void onHentStillingsprosentListeResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#hentTjenestepensjonForholdYtelse(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#hentTjenestepensjonForholdYtelse(DataObject aDataObject)	
	 */
	public void onHentTjenestepensjonForholdYtelseResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#hentTjenestepensjonInfo(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#hentTjenestepensjonInfo(DataObject aDataObject)	
	 */
	public void onHentTjenestepensjonInfoResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#lagreTjenestepensjonsforhold(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#lagreTjenestepensjonsforhold(DataObject aDataObject)	
	 */
	public void onLagreTjenestepensjonsforholdResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#lagreTjenestepensjonYtelse(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#lagreTjenestepensjonYtelse(DataObject aDataObject)	
	 */
	public void onLagreTjenestepensjonYtelseResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#opprettTjenestepensjonsforhold(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#opprettTjenestepensjonsforhold(DataObject aDataObject)	
	 */
	public void onOpprettTjenestepensjonsforholdResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#opprettTjenestepensjonSimulering(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#opprettTjenestepensjonSimulering(DataObject aDataObject)	
	 */
	public void onOpprettTjenestepensjonSimuleringResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#opprettTjenestepensjonYtelse(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#opprettTjenestepensjonYtelse(DataObject aDataObject)	
	 */
	public void onOpprettTjenestepensjonYtelseResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#lagreTjenestepensjonSimulering(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#lagreTjenestepensjonSimulering(DataObject aDataObject)	
	 */
	public void onLagreTjenestepensjonSimuleringResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#simulerOffentligTjenestepensjon(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon#simulerOffentligTjenestepensjon(DataObject aDataObject)	
	 */
	public void onSimulerOffentligTjenestepensjonResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler#hentSamhandler(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#hentSamhandler(DataObject aDataObject)	
	 */
	public void onHentSamhandlerResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler#lagreSamhandler(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#lagreSamhandler(DataObject aDataObject)	
	 */
	public void onLagreSamhandlerResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler#opprettSamhandler(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#opprettSamhandler(DataObject aDataObject)	
	 */
	public void onOpprettSamhandlerResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler#finnSamhandler(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#finnSamhandler(DataObject aDataObject)	
	 */
	public void onFinnSamhandlerResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

	/**
	 * Method generated to support the async implementation using callback
	 * for the operation (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler#hentEksternTilkoblingListe(DataObject aDataObject))
	 * of java interface (@link frg.lib.nav.no.nav.lib.frg.inf.Samhandler)	
	 * @see frg.lib.nav.no.nav.lib.frg.inf.Samhandler#hentEksternTilkoblingListe(DataObject aDataObject)	
	 */
	public void onHentEksternTilkoblingListeResponse(Ticket __ticket,
			DataObject returnValue, Exception exception) {
	}

}
