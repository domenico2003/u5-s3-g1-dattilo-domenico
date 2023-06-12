package it.epicode.be.prenotazioni.model.login;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

@Component
@Getter
public class UtilsMetods {

	// mi prendo le variabili dal file application.properties attraverso i setter
	// sotto
	private static String segreto;
	private static int durata;

	@Value("${segreto}")
	public void setSecret(String secretKey) {
		segreto = secretKey;
	}

	@Value("${durata}")
	public void setExpiration(String expirationInDays) {
		durata = Integer.parseInt(expirationInDays) * 24 * 60 * 60 * 1000;
	}

	// mi creo il token attraverso la classe Jwts
	static public String createToken(User u) {
		String token = Jwts.builder().setSubject(u.getId().toString())// inseriso nel body l'id dell'utente
				.setIssuedAt(new Date(System.currentTimeMillis()))// inserisco data di creazione
				.setExpiration(new Date(System.currentTimeMillis() + durata))// inserisco data di scadenza
				.signWith(Keys.hmacShaKeyFor(segreto.getBytes())).compact();// inserisco firma tramite segreto e
																			// compatto il tutto
		return token;
	}

	// metodo che verifica validità token
	static public void isTokenValid(String token) throws Exception {
		try {
			// verifico la validità token attraverso la stessa classe Jwts
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(segreto.getBytes())).build().parse(token);

		} catch (Exception e) {
			throw new Exception("Problemi col token, per favore effettua di nuovo il login");
		}
	}

	// mi estraggo id utente dal token attraverso la stessa classe Jwts
	static public String extractSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(segreto.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}

}
