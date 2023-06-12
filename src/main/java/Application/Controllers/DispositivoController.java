package Application.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import Application.Entities.Dispositivo;
import Application.Payloads.DispositivoPayload;
import Application.Services.DispositivoService;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

	@Autowired
	DispositivoService ds;

//CREATE
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo createDispositivo(@RequestBody DispositivoPayload dp) {
		return ds.createDispositivo(dp);
	}

//READ
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Page<Dispositivo> findAllDispositivi(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String ordina) {
		return ds.findAllDispositivi(page, ordina);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Dispositivo findDispositivoById(@PathVariable String id) {

		return ds.findbyDispositivoId(id);
	}

//DELETE
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDispositivoById(@PathVariable String id) {

		ds.deleteDispositivo(id);
	}

//UPDATE
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Dispositivo updateDispositivoById(@PathVariable String id, @RequestBody DispositivoPayload dp) {

		return ds.UpdateDispositivo(id, dp);
	}
}
