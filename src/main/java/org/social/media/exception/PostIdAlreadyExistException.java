package org.social.media.exception;

public class PostIdAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PostIdAlreadyExistException(String message) {
		super(message);
	}
}