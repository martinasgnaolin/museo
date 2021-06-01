package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.repository.CollezioneRepository;

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
	public List<Collezione> tutti() {
		return (List<Collezione>) collezioneRepo.findAll();
	}

	@Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepo.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
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
