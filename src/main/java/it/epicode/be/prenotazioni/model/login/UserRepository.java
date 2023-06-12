package it.epicode.be.prenotazioni.model.login;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Page<User> findByNome(String nome, Pageable pageable);

	Optional<User> findByUsername(String nome);

	Optional<User> findByEmail(String email);

}
