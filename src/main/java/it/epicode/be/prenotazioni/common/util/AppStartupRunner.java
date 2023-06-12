package it.epicode.be.prenotazioni.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.be.prenotazioni.model.login.UserRepository;
import it.epicode.be.prenotazioni.repository.CittaRepository;
import it.epicode.be.prenotazioni.repository.EdificioRepository;
import it.epicode.be.prenotazioni.repository.PostazioneRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppStartupRunner implements CommandLineRunner {

	@Autowired
	CittaRepository cittaRepository;

	@Autowired
	EdificioRepository edificioRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostazioneRepository postazioneRepository;

	@Override
	public void run(String... args) throws Exception {

	}

}