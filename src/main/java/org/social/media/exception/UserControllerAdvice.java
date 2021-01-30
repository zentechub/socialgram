package org.social.media.exception;

import java.util.Date;
import java.util.stream.Collectors;

import org.social.media.dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String errorMessage = ex.getBindingResult().getAllErrors().stream()
				.map(error -> error.getDefaultMessage())
				.collect(Collectors.joining(","));

		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Constraints Validation Failed", errorMessage);

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handleExceptionHandler(UserNotFoundException e) {
		return new ErrorDetails(new Date(), "User Not Found", e.getMessage());
	}
	@ExceptionHandler(value = FollowerNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handleExceptionHandler(FollowerNotFoundException e) {
		return new ErrorDetails(new Date(), "Follower not found for user", e.getMessage());
	}
	@ExceptionHandler(value = EmptyPostException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handleExceptionHandler(EmptyPostException e) {
		return new ErrorDetails(new Date(), "Post content is empty", e.getMessage());
	}
}
