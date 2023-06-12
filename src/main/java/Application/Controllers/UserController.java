package Application.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Application.Entities.User;
import Application.Payloads.UserPayload;
import Application.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService us;

//READ
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String ordina) {
		return us.findAllUser(page, ordina);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUsersByid(@PathVariable String id) {
		return us.findbyUserid(id);
	}

//delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable String id) {
		us.deleteUser(id);
	}

//UPDATE
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User updateUser(@PathVariable String id, @RequestBody UserPayload up) {
		return us.UpdateUser(id, up);
	}
}
