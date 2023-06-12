package it.epicode.be.prenotazioni.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.prenotazioni.model.Citta;
import it.epicode.be.prenotazioni.payloads.CittaPayload;
import it.epicode.be.prenotazioni.service.CittaService;
import it.epicode.be.prenotazioni.service.GeneralService;

@RestController
@RequestMapping("/citta")
public class CittaController {
	@Autowired
	GeneralService ps;
	@Autowired
	CittaService cs;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Citta cercaCittaByid(@PathVariable(required = false) String id) throws Exception {
		return ps.findCittaById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Citta modificaCittaByid(@PathVariable(required = false) String id, @RequestBody CittaPayload city)
			throws Exception {
		return cs.modificaCity(ps.findCittaById(id), city);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Citta aggiungiCitta(@RequestBody CittaPayload city) throws Exception {
		return cs.addCity(city);
	}

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Page<Citta> cercaAllCitta(@RequestParam int pagina) throws Exception {
		return cs.getTutteCitta(pagina);
	}
}
