package it.epicode.be.prenotazioni.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.be.prenotazioni.model.Citta;
import it.epicode.be.prenotazioni.model.Postazione;
import it.epicode.be.prenotazioni.model.TipoPostazione;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

	@Query("SELECT post FROM Postazione post where" + " post.edificio.citta = :citta AND post.tipo = :tipo"
			+ " AND post.id NOT IN (SELECT pre.postazione.id FROM Prenotazione pre where pre.dataPrenotata <> :dataRichiesta)")
	public List<Postazione> findLibereByCitta(Citta citta, TipoPostazione tipo, LocalDate dataRichiesta);

	public List<Postazione> findByEdificioCittaAndTipo(Citta citta, TipoPostazione tipoPostazione);

	public List<Postazione> findByCodice(String codice);

}
