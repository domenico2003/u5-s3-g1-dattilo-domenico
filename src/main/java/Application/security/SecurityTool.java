package Application.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import Application.Entities.User;
import Application.Exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class SecurityTool {
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

	static public String createToken(User u) {

		String token = Jwts.builder().setSubject(u.getId().toString()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + durata))
				.signWith(Keys.hmacShaKeyFor(segreto.getBytes())).compact();
		return token;
	}

	static public void validazioneToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(segreto.getBytes())).build().parse(token);

		} catch (Exception e) {
			throw new UnauthorizedException("Problemi col token, per favore effettua di nuovo il login");
		}
	}

	static public String estrazioneIdUtente(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(segreto.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
