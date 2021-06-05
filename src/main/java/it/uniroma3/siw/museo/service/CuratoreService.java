package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.repository.CuratoreRepository;

@Service
public class CuratoreService {
	
	@Autowired
	private CuratoreRepository curatoreRepo;
	
	@Transactional
	public Curatore save(Curatore curatore) {
		return curatoreRepo.save(curatore);
	}
		
	@Transactional
	public List<Curatore> cercaPerCognome(String cognome){
		return curatoreRepo.findByCognome(cognome);
	}
	
	@Transactional
	public List<Curatore> cercaPerNomeOCognome(String nome, String cognome){
		return curatoreRepo.findByNomeOrCognome(nome, cognome);
	}
	
	@Transactional
	public List<Curatore> cercaPerNomeECognome(String nome, String cognome){
		return curatoreRepo.findByNomeAndCognome(nome, cognome);
	}
	
	@Transactional
	public Curatore cercaPerEmail(String email){
		return curatoreRepo.findByEmail(email);
	}
	
	@Transactional
	public Curatore cercaPerMatricola(Long matricola){
		return curatoreRepo.findByMatricola(matricola);
	}
	
	@Transactional
	public boolean alreadyExists(Curatore curatore) {
		if (this.curatoreRepo.findByMatricola(curatore.getMatricola()) != null)
			return true;
		else 
			return false;
	}

	@Transactional
	public Curatore curatorePerId(Long id) {
		Optional<Curatore> optional = curatoreRepo.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public List<Curatore> tutti() {
		return (List<Curatore>)curatoreRepo.findAll();
	}


}
