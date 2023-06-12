package it.epicode.be.prenotazioni.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.prenotazioni.model.Postazione;
import it.epicode.be.prenotazioni.model.Prenotazione;
import it.epicode.be.prenotazioni.model.login.User;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

	public List<Prenotazione> findByUserAndDataPrenotata(User user, LocalDate dataPrenotata);

	public Page<Prenotazione> findByUser(User user, Pageable pageable);

	@Query("SELECT p FROM Prenotazione p WHERE p.dataPrenotata = :data AND p.postazione = :postazione")
	public List<Prenotazione> findPostazioneLibera(LocalDate data, Postazione postazione);

}
