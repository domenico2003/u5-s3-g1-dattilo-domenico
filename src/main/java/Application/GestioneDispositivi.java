package Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Application.Services.UserService;

@SpringBootApplication
public class GestioneDispositivi {
	static UserService us;

	@Autowired
	public void setUs(UserService userService) {
		this.us = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GestioneDispositivi.class, args);

		System.out.println("###################################################################################");
		System.out.println(us.findbyUserEmail("RanieroMontiFri Jun 09 21:26:19 CEST 2000@gmail.com"));
	}

}

//		Faker faker = new Faker(new Locale("it"));
//		for (int i = 0; i < 100; i++) {
//			UserPayload user = new UserPayload();
//			user.setCognome(faker.name().lastName());
//			user.setNome(faker.name().firstName());
//			user.setUsername(faker.name().username());
//			user.setEmail(faker.name().firstName() + faker.name().lastName() + faker.date().birthday() + "@gmail.com");
//			user.setPassword(faker.random().hex());
//
//			// us.createUser(user);
//		}