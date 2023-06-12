package it.epicode.be.prenotazioni.model.login;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.prenotazioni.payloads.UserPayload;

@RestController
@RequestMapping("/enter")
public class EnterController {
	@Autowired
	UtilsMetods um;
	@Autowired
	UserService us;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User registrati(@RequestBody UserPayload user) throws Exception {
		return us.addUser(user);
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TokenPayload> login(@RequestBody LoginRequest user) throws Exception {
		User users = us.findByEmail(user.getEmail());

		if (user.getPassword().equals(users.getPassword())) {
			String token = um.createToken(users);
			return new ResponseEntity<>(new TokenPayload(token), HttpStatus.OK);
		} else {
			throw new Exception("errore nell'inserimento dei campi");
		}

	}

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Page<User> allUser(@RequestParam int pagina) throws Exception {
		return us.getTuttiUser(pagina);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserByIdUser(@PathVariable long id) throws AccountNotFoundException {
		return us.findById(id);
	}

}
