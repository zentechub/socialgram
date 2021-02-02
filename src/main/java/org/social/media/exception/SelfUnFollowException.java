package org.social.media.exception;

public class SelfUnFollowException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public SelfUnFollowException(String message) {
		super(message);
	}
}