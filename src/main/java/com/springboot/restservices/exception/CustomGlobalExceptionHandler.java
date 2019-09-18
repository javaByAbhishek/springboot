package com.springboot.restservices.exception;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "from MethodArgumentNotValid in GEH",
				ex.getMessage());

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	// Handle Http Request Method Not Supported
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"from MethodArgumentNotValid in GEH -Method Not Supported", ex.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	// UserNameNotFoundException
	/*
	 * { "timestamp": "2019-09-18T07:46:21.758+0000", "message":
	 * "UserName raja not found in DB", "errorDetails": "uri=/users/byUserName/raja"
	 * }
	 */
	@ExceptionHandler(UserNameNotFoundException.class)
	protected ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

	// ConstraintViolationException
	/*
	 * { "timestamp": "2019-09-18T08:16:09.865+0000", "message":
	 * "getUserById.id: must be greater than or equal to 1", "errorDetails":
	 * "uri=/users/0" }
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

}
