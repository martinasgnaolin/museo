package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Credentials;
import it.uniroma3.siw.museo.model.Recensione;
import it.uniroma3.siw.museo.model.User;
import it.uniroma3.siw.museo.repository.RecensioneRepository;

@Service
public class RecensioneService {
	
	@Autowired
	private RecensioneRepository recensioneRepo;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public Recensione save(Recensione recensione) {
		return recensioneRepo.save(recensione);
	}
	
	@Transactional
	public List<Recensione> cercaPerUtente(User user) {
		return recensioneRepo.findByUtente(user);	
	}
	
	@Transactional
	public List<Recensione> tutti() {
		return (List<Recensione>) recensioneRepo.findAll();
	}

	@Transactional
	public Recensione recensionePerId(Long id) {
		Optional<Recensione> recensione = recensioneRepo.findById(id);
		if (recensione.isPresent())
			return recensione.get();
		else 
			return null;
	}

	public Credentials cercaCredenzialiPerUsername(String username) {
		return credentialsService.getCredentials(username);
	}

}
