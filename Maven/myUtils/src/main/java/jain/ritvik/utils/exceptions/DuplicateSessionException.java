package jain.ritvik.utils.exceptions;

public class DuplicateSessionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateSessionException() {
		super();
	}
	
	public DuplicateSessionException(String sError) {
		super(sError);
	}
}
