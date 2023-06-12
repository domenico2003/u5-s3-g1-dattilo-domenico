package Application.Payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DispositivoPayload {
	@NotNull
	String stato;
	@NotNull
	String dispositivo;
	@NotNull
	String nome;
	@NotNull
	String marca;
	@NotNull
	int ram;
	@NotNull
	String dimensioneSchermo;
	@NotNull
	String utenteId;
}
