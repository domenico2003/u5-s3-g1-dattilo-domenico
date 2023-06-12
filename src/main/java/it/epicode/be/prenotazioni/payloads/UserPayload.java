package it.epicode.be.prenotazioni.payloads;

import lombok.Getter;

@Getter
public class UserPayload {
	String username;
	String nome;
	String email;
	String password;
}
