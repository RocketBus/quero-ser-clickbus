package br.com.queroserclickbus.groupbyinterval.challenge.api.exceptionhandler;

import br.com.queroserclickbus.groupbyinterval.challenge.domain.exception.InvalidArgumentException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	                                                              HttpHeaders headers, HttpStatus status,
	                                                              WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		}

		Problem problem = createErrorBuilder(ErrorUtils.TITLE_SYNTAX_ERROR, status, "Invalid send format, " +
				"check syntax of sent data").build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
	                                                         HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder()
					.timestamp(LocalDateTime.now())
					.status(Long.valueOf(status.value()))
					.title(status.getReasonPhrase())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.timestamp(LocalDateTime.now())
					.status(Long.valueOf(status.value()))
					.title((String) body)
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
	                                                   HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());

		String detail = String.format("The property '%s' received the value '%s', "
						+ "of an invalid type. Correct and report a value compatible with type %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());

		Problem problem = createErrorBuilder(ErrorUtils.TITLE_SYNTAX_ERROR, status, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}


	private Problem.ProblemBuilder createErrorBuilder(String title, HttpStatus status, String detail) {
		return Problem.builder()
				.timestamp(LocalDateTime.now())
				.status(Long.valueOf(status.value()))
				.title(title)
				.detail(detail);
	}

	private String joinPath(List<JsonMappingException.Reference> references) {
		return references.stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
	}
}
