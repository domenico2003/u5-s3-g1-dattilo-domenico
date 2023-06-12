package it.epicode.be.prenotazioni.model.login;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.prenotazioni.payloads.UserPayload;

@Service
public class UserService {
	@Autowired
	UserRepository cr;

	public User addUser(UserPayload user) {
		User us = new User();
		us.setNome(user.getNome());
		us.setEmail(user.getEmail());
		us.setPassword(user.getPassword());
		us.setUsername(user.getUsername());
		return cr.save(us);
	}

	public User findById(long id) throws AccountNotFoundException {
		return cr.findById(id).orElseThrow(() -> new AccountNotFoundException());
	}

	public User findByEmail(String email) throws AccountNotFoundException {
		return cr.findByEmail(email).orElseThrow(() -> new AccountNotFoundException());
	}

	public Page<User> getTuttiUser(int page) {

		Pageable pagina = PageRequest.of(page, 10);

		return cr.findAll(pagina);
	}
}
