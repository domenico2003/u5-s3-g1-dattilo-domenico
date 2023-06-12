package it.epicode.be.prenotazioni.payloads;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PrenotazionePayload {
	@NotNull(message = "campo obligatorio")
	long idUtente;
	@NotNull(message = "campo obligatorio")
	long idPostazione;
	@NotNull(message = "campo obligatorio")
	LocalDate dataPrenotata;
}
