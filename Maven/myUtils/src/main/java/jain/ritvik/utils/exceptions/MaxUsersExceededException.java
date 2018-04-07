package jain.ritvik.utils.exceptions;

public class MaxUsersExceededException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MaxUsersExceededException() {
		super();
	}
	
	public MaxUsersExceededException(String sError) {
		super(sError);
	}
}
