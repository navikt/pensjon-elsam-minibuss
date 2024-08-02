package no.nav.service.stotte.sam;

import no.nav.domain.stotte.sam.exception.SamFunctionalRecoverableException;
import java.io.Serializable;


/**
 *Exception thrown when a sam message is already answered og the time limit has been exceeded 
 * 
 * 
 * 
 *
 */
public class SamAlreadyAnsweredOrTimeLimitExceededException extends SamFunctionalRecoverableException implements Serializable {

	private final Long samId;

	/**
	 * Gets the samId
	 * @return the samId
	 */
	public Long getSamId() {
		return samId;
	}

	/**
	 * Constructor.
	 * @param samId Long
	 */
	public SamAlreadyAnsweredOrTimeLimitExceededException(Long samId) {
		super("Message with id = " + samId + " has already been answered or time limit is exceeded.");
		this.samId = samId;
	}

}
