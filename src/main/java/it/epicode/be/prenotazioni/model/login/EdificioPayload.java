package it.epicode.be.prenotazioni.model.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdificioPayload {
	String nome;
	String indirizzo;
	String cidiceDisattivazione;
	long idcitta;
}
