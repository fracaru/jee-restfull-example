/**
 * 
 */
package org.example.jee.rest.common.service.exception;

/**
 * @author racaru
 * 
 */
public class UserServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new GalaxyService exception.
	 */
	public UserServiceException() {
		super();
	}

	/**
	 * Instantiates a new GalaxyService exception.
	 * 
	 * @param message
	 *            the message
	 */
	public UserServiceException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new GalaxyService exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new GalaxyService exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public UserServiceException(Throwable cause) {
		super(cause);
	}

}
