package Application.Services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Application.Entities.User;
import Application.Exceptions.NotFoundException;
import Application.Payloads.UserPayload;
import Application.Repositoryes.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository ur;

//CREATE
	public User createUser(UserPayload up) {

		User user = new User(up.getUsername(), up.getNome(), up.getEmail(), up.getCognome(), up.getPassword());
		return ur.save(user);
	}

//READ
	public User findbyUserid(String id) {

		Optional<User> user = ur.findById(UUID.fromString(id));
		return user.orElseThrow(() -> new NotFoundException("utente"));
	}

	public Page<User> findAllUser(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		Page<User> user = ur.findAll(pagina);
		return user;
	}

	public User findbyUserEmail(String email) {

		Optional<User> user = ur.findByEmail(email);
		return user.orElseThrow(() -> new NotFoundException("utente"));
	}

//DELETE
	public void deleteUser(String id) {

		User user = this.findbyUserid(id);
		ur.delete(user);
	}

//UPDATE
	public User UpdateUser(String id, UserPayload up) {

		User user = this.findbyUserid(id);
		user.setUsername(up.getUsername());
		user.setNome(up.getNome());
		user.setEmail(up.getEmail());
		user.setCognome(up.getCognome());
		user.setPassword(up.getPassword());

		return ur.save(user);
	}
}
