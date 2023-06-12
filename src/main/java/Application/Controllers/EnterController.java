package Application.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Application.Entities.User;
import Application.Exceptions.DatiErratiException;
import Application.Payloads.LoginPayload;
import Application.Payloads.TokenPayload;
import Application.Payloads.UserPayload;
import Application.Services.UserService;
import Application.security.SecurityTool;

@RestController
@RequestMapping("/enter")
public class EnterController {
	@Autowired
	UserService us;

	@PostMapping("/registration")
	public User createUser(@RequestBody UserPayload up) {
		return us.createUser(up);
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TokenPayload> createUser(@RequestBody LoginPayload up) {

		User user = us.findbyUserEmail(up.getEmail());

		if (user == null || !user.getPassword().matches(up.getPassword())) {
			throw new DatiErratiException();
		}

		String token = SecurityTool.createToken(user);

		return new ResponseEntity<TokenPayload>(new TokenPayload(token), HttpStatus.OK);
	}
}
