package it.epicode.be.prenotazioni.model.login;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.prenotazioni.payloads.UserPayload;
import it.epicode.be.prenotazioni.repository.EdificioRepository;

@Service
public class UserService {
	@Autowired
	UserRepository cr;
	@Autowired
	EdificioRepository er;

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

	public Edificio disattivaEdificio(long id, String codice) throws AccountNotFoundException {
		Edificio edificio = er.findById(id).orElseThrow(() -> new AccountNotFoundException());
		System.out.println(edificio.getCidiceDisattivazione());
		return edificio;
	}

	public Edificio addedificio(EdificioPayload user) {
		Edificio us = new Edificio();
		us.setNome(user.getNome());
		us.setCidiceDisattivazione(user.getCidiceDisattivazione());
		return er.save(us);
	}

	public Page<User> getTuttiUser(int page) {

		Pageable pagina = PageRequest.of(page, 10);

		return cr.findAll(pagina);
	}
}
