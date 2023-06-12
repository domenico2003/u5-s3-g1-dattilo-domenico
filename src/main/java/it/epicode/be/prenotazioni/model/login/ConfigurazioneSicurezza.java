package it.epicode.be.prenotazioni.model.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfigurazioneSicurezza {

	@Autowired
	TokenFiltro filtro;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//######################################################################################################################

		// disabilito provvisoriamente altre protezioni
		http.cors(c -> c.disable());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf(c -> c.disable());

//######################################################################################################################

		// indico a quali path consentire l'accesso senza autenticazione e quali invece
		// no
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/enter/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/citta/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/prenotazioni/**").authenticated());

//######################################################################################################################

		// indico dove posizionare il filtro,primo parametro il filtro secondo parametro
		// filtro al quale deve essere posizionato dopo
		http.addFilterBefore(filtro, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
