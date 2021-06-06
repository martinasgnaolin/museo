package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Credentials;
import it.uniroma3.siw.museo.model.Prenotazione;
import it.uniroma3.siw.museo.model.User;
import it.uniroma3.siw.museo.model.Visita;
import it.uniroma3.siw.museo.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prenotazioneRepo;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public Prenotazione save(Prenotazione prenotazione) {
		return prenotazioneRepo.save(prenotazione);
	}
	
	@Transactional
	public List<Prenotazione> cercaPerUtente(User utente){
		return prenotazioneRepo.findByUtente(utente);
	}
	
	@Transactional
	public List<Prenotazione> cercaPerVisita(Visita visita){
		return prenotazioneRepo.findByVisita(visita);
	}

	@Transactional
	public List<Prenotazione> tutti() {
		return (List<Prenotazione>) prenotazioneRepo.findAll();
	}

	@Transactional
	public Prenotazione prenotazionePerId(Long id) {
		Optional<Prenotazione> optional = prenotazioneRepo.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public boolean alreadyExists(Prenotazione prenotazione) {
		List<Prenotazione> prenotazioni = this.prenotazioneRepo.findByUtenteAndVisita(prenotazione.getUtente(), prenotazione.getVisita());
		if (prenotazioni.size() > 0)
			return true;
		else 
			return false;
	}
	
	public Collezione cercaCollezionePerId(Long id) {
		return collezioneService.collezionePerId(id);
	}
	
	public Credentials cercaCredenzialiPerUsername(String username) {
		return credentialsService.getCredentials(username);
	}


}
