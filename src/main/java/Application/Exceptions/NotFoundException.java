package Application.Exceptions;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	public NotFoundException(String elemento) {
		super(elemento + "non trovato!");
	}

}
