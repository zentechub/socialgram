package org.social.media.exception;

public class SelfFollowException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public SelfFollowException(String message) {
		super(message);
	}
}