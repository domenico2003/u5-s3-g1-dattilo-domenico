package it.epicode.be.prenotazioni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.prenotazioni.model.Citta;
import it.epicode.be.prenotazioni.payloads.CittaPayload;
import it.epicode.be.prenotazioni.repository.CittaRepository;

@Service
public class CittaService {
	@Autowired
	CittaRepository cr;

	public Page<Citta> getTutteCitta(int page) {

		Pageable pagina = PageRequest.of(page, 10);

		return cr.findAll(pagina);
	}

	public Citta addCity(CittaPayload city) {
		Citta citta = new Citta();
		citta.setNome(city.getName());
		return cr.save(citta);
	}

	public Citta modificaCity(Citta city, CittaPayload citaModificata) {
		city.setNome(citaModificata.getName());
		return cr.save(city);
	}
}
