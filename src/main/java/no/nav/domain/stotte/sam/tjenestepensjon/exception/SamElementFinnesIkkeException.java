package no.nav.domain.stotte.sam.tjenestepensjon.exception;

import no.nav.domain.stotte.sam.exception.SamFunctionalRecoverableException;

import java.io.Serializable;

/**
 * Sam domain exception for the bus exception FaultStoElementetFinnesIkke.
 * 
 */
public class SamElementFinnesIkkeException extends SamFunctionalRecoverableException implements Serializable {

	/**
	 * Constructs a SamElementFinnesIkkeException
	 * @param message Exception details
	 * @param cause cause of exception
	 */
	public SamElementFinnesIkkeException(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * Constructs a SamElementFinnesIkkeException 
	 * @param message Exception details
	 */
	public SamElementFinnesIkkeException(String message) {
		this(message, null);
	}
	
	

}
