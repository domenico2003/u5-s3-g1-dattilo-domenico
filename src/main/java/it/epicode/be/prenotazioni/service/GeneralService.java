package it.epicode.be.prenotazioni.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.prenotazioni.model.Citta;
import it.epicode.be.prenotazioni.model.Postazione;
import it.epicode.be.prenotazioni.model.Prenotazione;
import it.epicode.be.prenotazioni.model.TipoPostazione;
import it.epicode.be.prenotazioni.model.login.User;
import it.epicode.be.prenotazioni.model.login.UserRepository;
import it.epicode.be.prenotazioni.repository.CittaRepository;
import it.epicode.be.prenotazioni.repository.PostazioneRepository;
import it.epicode.be.prenotazioni.repository.PrenotazioneRepository;

@Service
public class GeneralService {
	@Autowired
	PostazioneRepository pr;
	@Autowired
	PrenotazioniService ps;
	@Autowired
	CittaRepository cr;
	@Autowired
	PrenotazioneRepository prenRep;
	@Autowired
	UserRepository ur;

	public List<Postazione> getByTipoAndCitta(String tp, String citta) throws Exception {
		Citta city = findCittaById(citta);
		List<Postazione> postazioni = pr.findByEdificioCittaAndTipo(city, TipoPostazione.valueOf(tp));
		return postazioni;
	}

	public Citta findCittaById(String id) throws Exception {

		if (cr.findById(Long.parseLong(id)).isPresent()) {
			return cr.findById(Long.parseLong(id)).get();
		} else {
			throw new Exception("citta non trovata");
		}

	}

	public User getUserById(long id) throws Exception {

		return ur.findById(id).orElseThrow(() -> new Exception("utente non trovato"));
	}

	public List<Prenotazione> getPrenotazioneByUserAndDataPrenotata(User user, LocalDate dataPrenotata)
			throws Exception {

		List<Prenotazione> postazioni = prenRep.findByUserAndDataPrenotata(user, dataPrenotata);
		return postazioni;
	}

	public Postazione getPostazioneById(long id) throws Exception {

		return pr.findById(id).orElseThrow(() -> new Exception("postazione non trovata"));
	}

	public List<Prenotazione> getPrenotazioneLibera(LocalDate data, Postazione postazione) throws Exception {

		return prenRep.findPostazioneLibera(data, postazione);
	}

	public Prenotazione createPrenotazione(long idUtente, long Idpostazione, LocalDate dataPrenotata) throws Exception {

		User user = this.getUserById(idUtente);
		Postazione pos = this.getPostazioneById(Idpostazione);
		Prenotazione pr = new Prenotazione(user, pos, dataPrenotata);
		boolean validato = false;
		long diffDays = ChronoUnit.DAYS.between(dataPrenotata, LocalDate.now());
		System.out.println(diffDays);
		if (!(diffDays >= -2)) {
			if (this.getPrenotazioneByUserAndDataPrenotata(user, dataPrenotata).isEmpty()) {
				if (this.getPrenotazioneLibera(dataPrenotata, pos).isEmpty()) {
					validato = true;
				}
			}
		}

		if (validato) {
			return prenRep.save(pr);
		} else {
			throw new Exception("prenotazione non salvata");
		}

	}

	public Prenotazione aggiornaPrenotazione(long idPrenotazione, long idUtente, long Idpostazione,
			LocalDate dataPrenotata) throws Exception {

		User user = this.getUserById(idUtente);
		Postazione pos = this.getPostazioneById(Idpostazione);
		Prenotazione pr = ps.getprenotazioneById(idPrenotazione);
		pr.setUser(user);
		pr.setPostazione(pos);
		pr.setDataPrenotata(dataPrenotata);
		boolean validato = false;
		long diffDays = ChronoUnit.DAYS.between(dataPrenotata, LocalDate.now());
		System.out.println(diffDays);
		if (!(diffDays >= -2)) {
			if (this.getPrenotazioneByUserAndDataPrenotata(user, dataPrenotata).isEmpty()) {
				if (this.getPrenotazioneLibera(dataPrenotata, pos).isEmpty()) {
					validato = true;
				}
			}
		}

		if (validato) {
			return prenRep.save(pr);
		} else {
			throw new Exception("prenotazione non aggiornata");
		}

	}

}