package Application.Exceptions;

@SuppressWarnings("serial")
public class DatiErratiException extends RuntimeException {

	public DatiErratiException() {
		super("dati inseriti non validi");

	}

}
