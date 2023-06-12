package it.epicode.be.prenotazioni.exceptions;

@SuppressWarnings("serial")
public class LanguageException extends RuntimeException {

	public LanguageException(String lingua) {
		super(lingua + " non disponibile!");
	}

}
