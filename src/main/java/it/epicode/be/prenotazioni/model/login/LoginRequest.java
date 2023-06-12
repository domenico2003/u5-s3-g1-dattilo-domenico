package it.epicode.be.prenotazioni.model.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginRequest {
	private String email;
	private String password;

}
