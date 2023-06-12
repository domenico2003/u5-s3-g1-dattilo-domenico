package it.epicode.be.prenotazioni;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.epicode.be.prenotazioni.model.login.User;
import it.epicode.be.prenotazioni.model.login.UserRepository;
import it.epicode.be.prenotazioni.model.login.UserService;

@SpringBootApplication
public class GestionePrenotazioniApplication {
	static UserService us;
	static UserRepository ur;
	static PasswordEncoder pe;

	@Autowired
	public void setUs(UserService userService) {
		this.us = userService;
	}

	@Autowired
	public void setPe(PasswordEncoder passEnc) {
		this.pe = passEnc;
	}

	@Autowired
	public void setUr(UserRepository usRep) {
		this.ur = usRep;
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionePrenotazioniApplication.class, args);
		// updateAll();
	}

	static void changeSinglePassword(long id) throws AccountNotFoundException {
		User user = us.findById(id);

		user.setPassword(pe.encode(user.getPassword()));

		ur.save(user);
	}

	static void updateAll() {
		ur.findAll().forEach(u -> {
			try {
				changeSinglePassword(u.getId());
			} catch (AccountNotFoundException e) {

				e.printStackTrace();
			}
		});
	}
}
