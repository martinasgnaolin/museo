package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepo;
	
	@Transactional
	public Collezione save(Collezione collezione) {
		return collezioneRepo.save(collezione);
	}
	
	@Transactional
	public List<Collezione> cercaPerNome(String nome){
		return collezioneRepo.findByNome(nome);
	}
	
	@Transactional
	public List<Collezione> cercaPerCuratore(Curatore curatore){
		return collezioneRepo.findByCuratore(curatore);
	}
	
	@Transactional
	public boolean alreadyExists(Collezione collezione) {
		List<Collezione> collezioni = this.collezioneRepo.findByNome(collezione.getNome());
		if (collezioni.size() > 0)
			return true;
		else 
			return false;
	}

}
