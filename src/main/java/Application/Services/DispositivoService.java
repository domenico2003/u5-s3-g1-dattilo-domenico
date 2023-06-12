package Application.Services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Application.Entities.Dispositivo;
import Application.Entities.User;
import Application.Entities.enums.StatoDispositivo;
import Application.Entities.enums.TipoDispositivo;
import Application.Exceptions.NotFoundException;
import Application.Payloads.DispositivoPayload;
import Application.Repositoryes.DispositivoRepository;

@Service
public class DispositivoService {
	@Autowired
	DispositivoRepository dr;

	@Autowired
	UserService us;

//CREATE
	public Dispositivo createDispositivo(DispositivoPayload dp) {
		User user = us.findbyUserid(dp.getUtenteId());
		Dispositivo disp = new Dispositivo(StatoDispositivo.valueOf(dp.getStato()),
				TipoDispositivo.valueOf(dp.getDispositivo()), dp.getNome(), dp.getMarca(), dp.getRam(),
				dp.getDimensioneSchermo(), user);
		return dr.save(disp);
	}

//READ
	public Dispositivo findbyDispositivoId(String id) {

		Optional<Dispositivo> disp = dr.findById(UUID.fromString(id));
		return disp.orElseThrow(() -> new NotFoundException("dispositivo"));
	}

	public Page<Dispositivo> findAllDispositivi(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		Page<Dispositivo> disp = dr.findAll(pagina);
		return disp;
	}

//DELETE
	public void deleteDispositivo(String id) {

		Dispositivo disp = this.findbyDispositivoId(id);
		dr.delete(disp);
	}

//UPDATE
	public Dispositivo UpdateDispositivo(String id, DispositivoPayload up) {

		Dispositivo disp = this.findbyDispositivoId(id);
		disp.setStato(StatoDispositivo.valueOf(up.getStato()));
		disp.setDispositivo(TipoDispositivo.valueOf(up.getDispositivo()));
		disp.setNome(up.getNome());
		disp.setMarca(up.getMarca());
		disp.setRam(up.getRam());
		disp.setDimensioneSchermo(up.getDimensioneSchermo());
		User user = us.findbyUserid(up.getUtenteId());
		disp.setUtenteAssegnato(user);
		return dr.save(disp);
	}
}
