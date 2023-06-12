package it.epicode.be.prenotazioni.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.prenotazioni.model.Citta;

public interface CittaRepository extends JpaRepository<Citta, Long> {

	public Page<Citta> findByNome(String nome, Pageable pageable);

}
