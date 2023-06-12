package Application.Repositoryes;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import Application.Entities.User;

@Component
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
}
