package no.nav.domain.stotte.sam.trekk.exception;

import no.nav.domain.stotte.sam.exception.SamFunctionalRecoverableException;

/**
 * Trekk is not legal. Note that this exception might be refined into more
 * detailed exceptions when Trekk consumer is available.
 * 
 * @version $id$
 */
public class SamUlovligTrekkException extends SamFunctionalRecoverableException {

	/**
	 * Constructor to create a SamUlovligTrekkException.
	 * 
	 * @param message Exception details
	 * @param cause Casue of exception
	 */
	public SamUlovligTrekkException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Creates a new exception
	 * 
	 * @param message Exception details
	 */
	public SamUlovligTrekkException(String message) {
		this(message, null);
	}	


	
	

}
