package no.nav.domain.stotte.sam.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * Super class for exceptions thrown from a sam process when the process is not
 * able to execute due to functional failures.
 * 
 * @version $id$
 */
public abstract class SamFunctionalRecoverableException extends FunctionalRecoverableException {
	
	/**
	 * Constructs a new SamFunctionalException with a message and cause.
	 * 
	 * @param message The fault message.
	 * @param cause The Throwable object.
	 */
	public SamFunctionalRecoverableException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new SamFunctionalException with a message.
	 * 
	 * @param message The fault message.
	 */
	public SamFunctionalRecoverableException(String message) {
		super(message);
	}
	
}
