package Application.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import Application.Payloads.ErrorsPayload;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorsPayload> handleUnauthorized(UnauthorizedException e) {

		ErrorsPayload payload = new ErrorsPayload(e.getMessage(), new Date(), 401);

		return new ResponseEntity<ErrorsPayload>(payload, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorsPayload> handleNotFound(NotFoundException e) {

		ErrorsPayload payload = new ErrorsPayload(e.getMessage(), new Date(), 404);

		return new ResponseEntity<ErrorsPayload>(payload, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorsPayload> handleGeneric(Exception e) {
		System.out.println(e);
		ErrorsPayload payload = new ErrorsPayload("Errore Generico", new Date(), 500);

		return new ResponseEntity<ErrorsPayload>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
