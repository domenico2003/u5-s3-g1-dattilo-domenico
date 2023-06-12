package it.epicode.be.prenotazioni.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.epicode.be.prenotazioni.exceptions.LanguageException;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LanguageException.class)
	public ResponseEntity<ApiError> handleNotFound(LanguageException e) {
		ApiError payload = new ApiError(e.getMessage(), 404, HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiError>(payload, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> genericError(Exception e) {
		ApiError payload = new ApiError("GENERIC SERVER ERROR! WE GONNA FIX IT ASAP", 500,
				HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ApiError>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}