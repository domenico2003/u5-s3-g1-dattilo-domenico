package it.epicode.be.prenotazioni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.epicode.be.prenotazioni.model.Prenotazione;
import it.epicode.be.prenotazioni.repository.PrenotazioneRepository;

@Service

public class PrenotazioniService {
	@Autowired
	PrenotazioneRepository prenRep;

	public Prenotazione getprenotazioneById(long id) throws Exception {
		return prenRep.findById(id).orElseThrow(() -> new Exception("utente non trovato"));
	}

	public void deleteprenotazioneById(long id) throws Exception {

		prenRep.delete(this.getprenotazioneById(id));

	}

	public Page<Prenotazione> getprenotazioni(int pagina) throws Exception {
		PageRequest page = PageRequest.of(pagina, 10);
		return prenRep.findAll(page);
	}
}
