package org.social.media.exception;

public class EmptyPostException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EmptyPostException(String message) {
		super(message);
	}
}
