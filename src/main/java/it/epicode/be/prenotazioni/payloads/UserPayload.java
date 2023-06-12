package it.epicode.be.prenotazioni.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPayload {
	String username;
	String nome;
	String email;
	String password;
}
