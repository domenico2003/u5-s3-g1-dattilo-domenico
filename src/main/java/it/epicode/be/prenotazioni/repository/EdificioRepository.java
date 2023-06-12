package it.epicode.be.prenotazioni.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.prenotazioni.model.Citta;
import it.epicode.be.prenotazioni.model.Edificio;

public interface EdificioRepository extends JpaRepository<Edificio, Long> {

	Page<Edificio> findByCitta(Citta citta, Pageable pageable);

	Page<Edificio> findByNome(String nome, Pageable pageable);

}
