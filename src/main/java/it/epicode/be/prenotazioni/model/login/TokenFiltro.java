package it.epicode.be.prenotazioni.model.login;

import java.io.IOException;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//estendo la classe OncePerRequestFilter per creare il filtro
@Component
public class TokenFiltro extends OncePerRequestFilter {
	@Autowired
	UserService us;

	// metodo implementato automaticamente e che verrà chiamato a ogni richiesta
	// il seguente metodo serve per creare un filtro sui token e viene illustrato
	// con commenti step by step

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//############################################## primo step ###############################################################		

		// estraggo il token non pulito(con "Bearer ")
		String tokenConBearer = request.getHeader("Authorization");

//############################################## secondo step ###############################################################		

		// prima verifica che il token sia valido
		if (tokenConBearer == null || !tokenConBearer.startsWith("Bearer ")) {
			throw new AuthenticationServiceException("Problemi col token, per favore effettua di nuovo il login");
		}

//############################################## terzo step ###############################################################		

		// mi estraggo il token puro(senza "Bearer ")
		String tokenPuro = tokenConBearer.substring(7);

//############################################## quarto step ###############################################################		

		// verifico che il token sia valido tramite metodo creato da me isTokenValid in
		// UtilsMetods
		try {
			UtilsMetods.isTokenValid(tokenPuro);
		} catch (Exception e) {
			throw new AuthenticationServiceException("Problemi col token, per favore effettua di nuovo il login");
		}

//############################################## quinto step ###############################################################		

		// mi estraggo l'id tramite metodo creato da me extractSubject in UtilsMetods
		String idUser = UtilsMetods.extractSubject(tokenPuro);

//############################################## sesto step ###############################################################		

		// mi cerco l'utente tramite id estratto dal token
		try {
			User user = us.findById(Long.parseLong(idUser));

//############################################## settimo step ###############################################################		

			// inserisco utente tra gli utenti loggati "SecurityContextHolder"
			UsernamePasswordAuthenticationToken loggaUtente = new UsernamePasswordAuthenticationToken(user, null,
					user.getAuthorities());
			loggaUtente.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(loggaUtente);

//############################################## ottavo step ###############################################################		

			// passo al prossimo filtro
			filterChain.doFilter(request, response);
		} catch (AccountNotFoundException e) {

			throw new AuthenticationServiceException("utente non trovato");
		}

	}

	// metodo per evitare esecuzione filtro su alcuni endpoint

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return new AntPathMatcher().match("/enter/**", request.getServletPath());
	}

}
