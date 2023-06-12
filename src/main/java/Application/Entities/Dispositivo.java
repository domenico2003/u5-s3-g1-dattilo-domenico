package Application.Entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import Application.Entities.enums.StatoDispositivo;
import Application.Entities.enums.TipoDispositivo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Dispositivo {
	@Id
	@GeneratedValue
	private UUID id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatoDispositivo stato;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoDispositivo dispositivo;
	@NotNull
	private String nome;
	@NotNull
	private String marca;
	@NotNull
	private int ram;
	@NotNull
	private String dimensioneSchermo;
	@ManyToOne
	@JsonBackReference
	private User utenteAssegnato;

	@Override
	public String toString() {
		return nome + " [id=" + id + ", stato=" + stato + ", dispositivo=" + dispositivo + ", nome=" + nome + ", marca="
				+ marca + ", ram=" + ram + ", dimensioneSchermo=" + dimensioneSchermo + "]";
	}

	public Dispositivo(@NotNull StatoDispositivo stato, @NotNull TipoDispositivo dispositivo, @NotNull String nome,
			@NotNull String marca, @NotNull int ram, @NotNull String dimensioneSchermo, User utenteAssegnato) {

		this.stato = stato;
		this.dispositivo = dispositivo;
		this.nome = nome;
		this.marca = marca;
		this.ram = ram;
		this.dimensioneSchermo = dimensioneSchermo;
		this.utenteAssegnato = utenteAssegnato;
	}

}
