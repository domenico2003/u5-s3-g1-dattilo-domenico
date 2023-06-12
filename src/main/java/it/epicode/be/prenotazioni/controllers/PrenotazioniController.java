package it.epicode.be.prenotazioni.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.prenotazioni.exceptions.LanguageException;
import it.epicode.be.prenotazioni.model.Postazione;
import it.epicode.be.prenotazioni.model.Prenotazione;
import it.epicode.be.prenotazioni.payloads.DeletePayload;
import it.epicode.be.prenotazioni.payloads.PrenotazionePayload;
import it.epicode.be.prenotazioni.service.GeneralService;
import it.epicode.be.prenotazioni.service.PrenotazioniService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {
	@Autowired
	GeneralService ps;
	@Autowired
	PrenotazioniService pgs;

	@GetMapping("/infoprenotazioni")
	@ResponseStatus(HttpStatus.OK)
	public String getinfo(@RequestParam String lingua) {

		if (lingua.contains("italiano")) {
			return "per effettuare la prenotazione inserire l'utente, la postazione desiderata ,la data prenotata e ladata di prenotazione";
		} else if (lingua.contains("inglese")) {
			return "to make a reservation enter the user, the desired station, the date booked and the date of booking";
		} else {
			throw new LanguageException(lingua);
		}

	}

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<Postazione> cercaPostazioniPerTipoEcitta(@RequestParam(required = false) String città,
			@RequestParam(required = false) String tipopostazione) throws Exception {
		return ps.getByTipoAndCitta(tipopostazione, città);
	}

	@GetMapping("/{pagina}")
	@ResponseStatus(HttpStatus.OK)
	public Page<Prenotazione> cercaAllPostazioni(@PathVariable int pagina) throws Exception {
		return pgs.getprenotazioni(pagina);
	}

	@GetMapping("/byId")
	@ResponseStatus(HttpStatus.OK)
	public Prenotazione cercaPostazioniByid(@RequestParam(required = false) long id) throws Exception {
		return pgs.getprenotazioneById(id);
	}

	@DeleteMapping("/byId")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DeletePayload> deletePrenotazione(@RequestParam(required = false) long id) throws Exception {

		pgs.deleteprenotazioneById(id);

		DeletePayload dp = new DeletePayload("eliminazione prenotazione con id: " + id + " avvenuta con successo", 204,
				HttpStatus.NO_CONTENT);
		return new ResponseEntity<DeletePayload>(dp, HttpStatus.OK);

	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Prenotazione createPrenotazione(@RequestBody @Validated PrenotazionePayload pr) throws Exception {
		return ps.createPrenotazione(pr.getIdUtente(), pr.getIdPostazione(), pr.getDataPrenotata());
	}

	@PutMapping("/byId")
	@ResponseStatus(HttpStatus.OK)
	public Prenotazione aggiornaPrenotazione(@RequestBody @Validated PrenotazionePayload pr, @RequestParam long id)
			throws Exception {
		return ps.aggiornaPrenotazione(id, pr.getIdUtente(), pr.getIdPostazione(), pr.getDataPrenotata());
	}
}
