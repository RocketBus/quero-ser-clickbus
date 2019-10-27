package br.com.click.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	private String INCORRECT_REQUEST = "NOT_FOUND";
	private String BAD_REQUEST = "BAD_REQUEST";

	@ExceptionHandler(PlaceNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleUserNotFoundException(PlaceNotFoundException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingHeaderInfoException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(MissingHeaderInfoException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<>();

		for (final FieldError field : exception.getBindingResult().getFieldErrors()) {
			details.add(field.getField() + ": " + field.getDefaultMessage());
		}
		for (final ObjectError obj : exception.getBindingResult().getGlobalErrors()) {
			details.add(obj.getObjectName() + ": " + obj.getDefaultMessage());
		}

		return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST, details), HttpStatus.BAD_REQUEST);
	}
}