package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering;

import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.*;
import nav_lib_frg.no.nav.lib.frg.gbo.GBOAvdeling;
import nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnSamhandlerRequest;
import nav_lib_frg.no.nav.lib.frg.gbo.GBOSamhandler;
import nav_lib_frg.no.nav.lib.frg.gbo.GBOSamhandlerListe;
import nav_lib_frg.no.nav.lib.frg.inf.Samhandler;
import no.nav.elsam.tpsamordningregistrering.v0_5.*;
import no.nav.elsam.tpsamordningregistrering.v1_0.HentSamordningsdataResp;
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResp;
import no.nav.pensjon.elsam.minibuss.ServiceBusinessException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class NavConsElsamTplibTpSamordningRegistrering {
	private final static String INTERNALERROR = "InternalError";

	private final Samhandler samhandlerPartner;
	private final TPSamordningRegistreringInt tpSamordningRegistreringIntPartner;

    public NavConsElsamTplibTpSamordningRegistrering(Samhandler samhandlerPartner, TPSamordningRegistreringInt tpSamordningRegistreringIntPartner) {
        this.samhandlerPartner = samhandlerPartner;
        this.tpSamordningRegistreringIntPartner = tpSamordningRegistreringIntPartner;
    }

    public LagreTPYtelseResp lagreTPYtelse(LagreTPYtelseReq lagreTPYtelseReq) throws LagreTPYtelseIntFaultGeneriskMsg, LagreTPYtelseIntFaultTPYtelseAlleredeRegistrertMsg, ServiceBusinessException {
		// Map TP-nummer to tssEksternId
		String eksternTSSId = mapTPnrToTSSEksternId(lagreTPYtelseReq.getTpnr());

		// Copy external interface to internal and set externalTSSId
		LagreTPYtelseReqInt intRequest = new LagreTPYtelseReqInt();
		intRequest.setExtRequest(lagreTPYtelseReq);
		intRequest.setTssEksternId(eksternTSSId);

		// Call entity service
		try {
			return tpSamordningRegistreringIntPartner.lagreTPYtelseInt(intRequest);
		} catch (RuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(), NestedExceptionUtils.getMostSpecificCause(e).toString());
		}
	}

	public void slettTPYtelse(SlettTPYtelseReq slettTPYtelseReq) throws SlettTPYtelseIntFaultGeneriskMsg, SlettTPYtelseIntFaultTPYtelseIkkeFunnetMsg, ServiceBusinessException {
		// Map TP-nummer to tssEksternId
		String eksternTSSId = mapTPnrToTSSEksternId(slettTPYtelseReq.getTpnr());

		// Copy external interface to internal and set externalTSSId
		SlettTPYtelseReqInt intRequest = new SlettTPYtelseReqInt();
		intRequest.setExtRequest(slettTPYtelseReq);
		intRequest.setTssEksternId(eksternTSSId);

		// Call entity service
		try {
			tpSamordningRegistreringIntPartner.slettTPYtelseInt(intRequest);
		} catch (RuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(), NestedExceptionUtils.getMostSpecificCause(e).toString());
		}
	}

	public HentSamordningsdataResp hentSamordningsdata(HentSamordningsdataReq hentSamordningsdataReq) throws HentSamordningsdataIntFaultTPForholdIkkeIverksattMsg, HentSamordningsdataIntFaultGeneriskMsg, ServiceBusinessException {
		// Map TP-nummer to tssEksternId
		String eksternTSSId = mapTPnrToTSSEksternId(hentSamordningsdataReq.getTpnr());

		// Copy external interface to internal and set externalTSSId
		HentSamordningsdataReqInt intRequest = new HentSamordningsdataReqInt();
		intRequest.setExtRequest(hentSamordningsdataReq);
		intRequest.setTssEksternId(eksternTSSId);

		// Call entity service
		try {
			return tpSamordningRegistreringIntPartner.hentSamordningsdataInt(intRequest);
		} catch (RuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(), NestedExceptionUtils.getMostSpecificCause(e).toString());
		}
	}

	public void opprettRefusjonskrav(OpprettRefusjonskravReq opprettRefusjonskravReq) throws ServiceBusinessException, OpprettRefusjonskravIntFaultSamordningsIdOgPersonKorrelererIkkeMsg, OpprettRefusjonskravIntFaultAlleredeMottattRefusjonskravMsg, OpprettRefusjonskravIntFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg, OpprettRefusjonskravIntFaultSamordningsIdIkkeFunnetMsg, OpprettRefusjonskravIntFaultRefusjonskravUtenforTidsfristMsg, OpprettRefusjonskravIntFaultGeneriskMsg {
		// Map TP-nummer to tssEksternId
		String eksternTSSId = mapTPnrToTSSEksternId(opprettRefusjonskravReq.getTpnr());

		// Copy external interface to internal and set externalTSSId
		OpprettRefusjonskravReqInt intRequest = new OpprettRefusjonskravReqInt();
		intRequest.setExtRequest(opprettRefusjonskravReq);
		intRequest.setTssEksternId(eksternTSSId);

		// Call entity service
		try {
			tpSamordningRegistreringIntPartner.opprettRefusjonskravInt(intRequest);
		} catch (RuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(), NestedExceptionUtils.getMostSpecificCause(e).toString());
		}
	}

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
	private String mapTPnrToTSSEksternId(String tpNr) throws ServiceBusinessException {
		// Build request object for samhandler
		GBOFinnSamhandlerRequest samhandlerRequest = new GBOFinnSamhandlerRequest();
		samhandlerRequest.setOffentligId(tpNr);
		samhandlerRequest.setIdType("TPNR");
		samhandlerRequest.setSamhandlerType("TEPE");

		GBOSamhandlerListe samhandlerResponse = null;
		try {
			samhandlerResponse = samhandlerPartner.finnSamhandler(samhandlerRequest);
		} catch (RuntimeException e) {
			throw createTechnicalFault(INTERNALERROR, e.getMessage(), NestedExceptionUtils.getMostSpecificCause(e).toString());
		}

		if (samhandlerResponse == null) {
			throw createTechnicalFault(
					INTERNALERROR,
					"Det oppstod en feil under mapping fra TP-nummer til intern ID",
					"Tomt svar ved oppslag av samhandler");
		}

		List<GBOSamhandler> samhandlere = samhandlerResponse.getSamhandlere();
		if (samhandlere.size() > 1) {
			// Got more than 1 samhandler in the response. Should receive only
			// 1.
			throw createTechnicalFault(
					INTERNALERROR,
					"Det oppstod en feil under mapping fra TP-nummer til intern ID",
					"Ikke entydig treff på TP-nr");
		} else if (samhandlere.size() == 1) {
			GBOSamhandler samhandler = samhandlere.get(0);

			// Store idTSSEkstern in class variable for later use in other
			// methods
			List<GBOAvdeling> avdelinger = samhandler.getAvdelinger();
			if (avdelinger == null) {
				throw createTechnicalFault(
						INTERNALERROR,
						"Det oppstod en feil under mapping fra TP-nummer til intern ID",
						"Fant ingen avdelinger på samhandleren");
			}
			for (Iterator<GBOAvdeling> iter = avdelinger.iterator(); iter.hasNext();) {
				GBOAvdeling avdeling = iter.next();
				if (avdeling != null) {
					if (avdeling.getAvdelingsnr().equals("01")) {
						return avdeling.getIdTSSEkstern();
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

	private ServiceBusinessException createTechnicalFault(String errorCode, String errorDescription, String errorDetail) {
		return createTechnicalFault(errorCode, errorDescription, List.of(errorDetail));
	}

	private ServiceBusinessException createTechnicalFault(String errorCode, String errorDescription, List errorDetails) {
		FaultGenerisk faultBO = new FaultGenerisk();
		faultBO.setErrorCode(errorCode);
		faultBO.setErrorDescription(errorDescription);
		faultBO.getErrorDetails().addAll(errorDetails);
		return new ServiceBusinessException(faultBO);
	}

}
