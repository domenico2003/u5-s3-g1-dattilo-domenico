package it.epicode.be.prenotazioni.payloads;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeletePayload {

	String message;
	int statusCode;
	HttpStatus status;
}
