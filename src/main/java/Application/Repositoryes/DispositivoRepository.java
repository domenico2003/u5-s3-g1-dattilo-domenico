package Application.Repositoryes;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import Application.Entities.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {

}
