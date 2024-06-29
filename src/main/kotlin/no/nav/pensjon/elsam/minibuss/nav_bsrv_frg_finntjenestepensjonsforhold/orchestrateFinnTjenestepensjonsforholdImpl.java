package no.nav.pensjon.elsam.minibuss.nav_bsrv_frg_finntjenestepensjonsforhold;

import nav_lib_frg.no.nav.lib.frg.gbo.*;
import nav_lib_frg.no.nav.lib.frg.inf.HentSamhandlerFaultSamhandlerIkkeFunnetMsg;
import nav_lib_frg.no.nav.lib.frg.inf.Samhandler;
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg;
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg;
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon;

import java.util.Iterator;
import java.util.List;

public class orchestrateFinnTjenestepensjonsforholdImpl {
	private Samhandler samhandlerPartner;
	private Tjenestepensjon tjenestepensjonPartner;

	public Tjenestepensjon locateService_TjenestepensjonPartner() {
		return tjenestepensjonPartner;
	}

	public Samhandler locateService_SamhandlerPartner() {
		return samhandlerPartner;
	}

	public GBOTjenestepensjon finnTjenestepensjonsforhold(GBOFinnTjenestepensjonsforholdRequest finnTjenestepensjonsforholdRequest) throws HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg, HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg {
		GBOTjenestepensjon finnTjenestepensjonsforholdResponse = null;
		if (finnTjenestepensjonsforholdRequest != null) {
			//Error handling is implicit, since the exact same "fault-BO's" (java-exceptions in the run-time)
			//is sent out from the POJO as is received in to it. The exceptions float up to the cons-layer
			//automatically.

			//Check which search-parameter is given, and invoke the corresponding operation
			if (finnTjenestepensjonsforholdRequest.getFnr() != null) {
				finnTjenestepensjonsforholdResponse = locateService_TjenestepensjonPartner().hentTjenestepensjonInfo(mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonInfoRequest(finnTjenestepensjonsforholdRequest));
			}
			else if (finnTjenestepensjonsforholdRequest.getForholdId() != null) {
				finnTjenestepensjonsforholdResponse = locateService_TjenestepensjonPartner().hentTjenestepensjonForholdYtelse(mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(finnTjenestepensjonsforholdRequest));
			}
			else if (finnTjenestepensjonsforholdRequest.getYtelseId() != null) {
				finnTjenestepensjonsforholdResponse = locateService_TjenestepensjonPartner().hentTjenestepensjonForholdYtelse(mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(finnTjenestepensjonsforholdRequest));
			}
			
			//IF hentSamhandlerInfo is true get samhandler-info for all tjenestepensjonForhold's in the response
			if("true".equalsIgnoreCase(finnTjenestepensjonsforholdRequest.getHentSamhandlerInfo())) {
				finnTjenestepensjonsforholdResponse = fillGBOTjenestepensjonWithSamhandlerInfo(finnTjenestepensjonsforholdResponse);
			}

		}
		return finnTjenestepensjonsforholdResponse;

	}
	

	// ******************
	// UTILITY METHODS
	// ******************
	
	private GBOHentTjenestepensjonInfoRequest mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonInfoRequest(GBOFinnTjenestepensjonsforholdRequest GBOFinnTjenestepensjonsforholdRequest) {
		GBOHentTjenestepensjonInfoRequest GBOHentTjenestepensjonInfoRequest = null;
		
		if (GBOFinnTjenestepensjonsforholdRequest != null) {
			GBOHentTjenestepensjonInfoRequest = new GBOHentTjenestepensjonInfoRequest();
			GBOHentTjenestepensjonInfoRequest.setFnr(GBOFinnTjenestepensjonsforholdRequest.getFnr());
			GBOHentTjenestepensjonInfoRequest.setDatoFom(GBOFinnTjenestepensjonsforholdRequest.getFom());
			GBOHentTjenestepensjonInfoRequest.setDatoTom(GBOFinnTjenestepensjonsforholdRequest.getTom());
			GBOHentTjenestepensjonInfoRequest.setTssEksternId(GBOFinnTjenestepensjonsforholdRequest.getTssEksternId());
		}
		
		return GBOHentTjenestepensjonInfoRequest;
	}
	
	private GBOHentTjenestepensjonForholdYtelseRequest mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(GBOFinnTjenestepensjonsforholdRequest GBOFinnTjenestepensjonsforholdRequest) {
		GBOHentTjenestepensjonForholdYtelseRequest GBOHentTjenestepensjonForholdYtelseRequest = null;
		
		if (GBOFinnTjenestepensjonsforholdRequest != null) {
			GBOHentTjenestepensjonForholdYtelseRequest = new GBOHentTjenestepensjonForholdYtelseRequest();
			GBOHentTjenestepensjonForholdYtelseRequest.setForholdId(GBOFinnTjenestepensjonsforholdRequest.getForholdId());
			GBOHentTjenestepensjonForholdYtelseRequest.setYtelseId(GBOFinnTjenestepensjonsforholdRequest.getYtelseId());
		}
		
		return GBOHentTjenestepensjonForholdYtelseRequest;
	}
	
	private GBOTjenestepensjon fillGBOTjenestepensjonWithSamhandlerInfo(GBOTjenestepensjon tjenestepensjon) {
		if (tjenestepensjon != null) {
			List<GBOTjenestepensjonForhold> tjenestepensjonForholdList = tjenestepensjon.getTjenestepensjonForholdene();
			
			if (tjenestepensjonForholdList != null) {
				GBOTjenestepensjonForhold tjenestepensjonForhold = null;
				GBOHentSamhandlerRequest hentSamhandlerRequest = new GBOHentSamhandlerRequest();
				GBOSamhandler hentSamhandlerResponse = null;
				List<GBOAvdeling> samhandlerAvdelingList = null;
				GBOAvdeling samhandlerAvdeling = null;
				
				//Iterate through received list to fill in samhandlerInfo on each item in the list
				for (Iterator<GBOTjenestepensjonForhold> iter = tjenestepensjonForholdList.iterator(); iter.hasNext();) {
					tjenestepensjonForhold = iter.next();
					if (tjenestepensjonForhold != null) {
						//Create request-object for hentSamhandler-call
						hentSamhandlerRequest.setIdTSSEkstern(tjenestepensjonForhold.getTssEksternId());
						hentSamhandlerRequest.setHentDetaljert(true);
						//Call hentSamhandler to get samhandlerInfo
						try {
							hentSamhandlerResponse = locateService_SamhandlerPartner().hentSamhandler(hentSamhandlerRequest);
							if (hentSamhandlerResponse != null) {
															
								//Get alternativId from the response's list alternativeIder where alternativeIdKode is "TPNR"
								GBOAlternativId altId = null;
								List<GBOAlternativId> altIdList = hentSamhandlerResponse.getAlternativeIder();
								if (altIdList != null) {
									boolean done = false;
									for (Iterator<GBOAlternativId> i = altIdList.iterator() ; i.hasNext()&& !done;) {
										altId = i.next();
										if (altId != null && altId.getAlternativKode() != null && altId.getAlternativKode().equals("TPNR")) {
											tjenestepensjonForhold.setTpnr(altId.getAlternativId());
											done=true;
										}
									}
								}	
								
								//Get avdeling from the response and read avdelingNavn from avdeling.
								samhandlerAvdelingList = hentSamhandlerResponse.getAvdelinger();
								if (samhandlerAvdelingList != null) { 
									//The number of entries in this list will always be 1 for the call used here 
									samhandlerAvdeling = samhandlerAvdelingList.get(0);
									if (samhandlerAvdeling != null)
										tjenestepensjonForhold.setNavn(samhandlerAvdeling.getAvdelingNavn());
									    tjenestepensjonForhold.setAvdelingType(samhandlerAvdeling.getAvdelingType());
								}
							}
						} catch (HentSamhandlerFaultSamhandlerIkkeFunnetMsg sbe) {
							//hentSamhandler might return FaultSamhandlerIkkeFunnet. This fault 
							//is ignored and processing continuous with next item in the list.
						}
					}
				}
			}
		}
		
		return tjenestepensjon;
	}
}
