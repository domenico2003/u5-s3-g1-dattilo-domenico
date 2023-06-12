package it.epicode.be.prenotazioni.model.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.epicode.be.prenotazioni.model.Citta;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({ "cidiceDisattivazione" })
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String indirizzo;

//chiamo il convertitore che mi cripta e mi decripta automaticamente
	@Convert(converter = Convertering.class)
	private String cidiceDisattivazione;

	private String stato = "ATTIVO";
	@ManyToOne
	private Citta citta;
}
