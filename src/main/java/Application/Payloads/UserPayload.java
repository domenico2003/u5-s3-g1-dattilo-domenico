package Application.Payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPayload {

	@NotNull
	String username;
	@NotNull
	String nome;
	@NotNull
	String email;
	@NotNull
	String cognome;
	@NotNull
	String password;
}
