package it.epicode.be.prenotazioni.model;

import java.time.LocalDate;

import org.springframework.lang.NonNull;

import it.epicode.be.prenotazioni.model.login.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NonNull
	private User user;

	@ManyToOne
	@NonNull
	private Postazione postazione;

	@NonNull
	private LocalDate dataPrenotata;

	private LocalDate dataPrenotazione;

	public Prenotazione(User user, Postazione postazione, LocalDate dataPrenotata) {
		super();
		this.user = user;
		this.postazione = postazione;
		this.dataPrenotata = dataPrenotata;
		this.dataPrenotazione = LocalDate.now();
	}

}
