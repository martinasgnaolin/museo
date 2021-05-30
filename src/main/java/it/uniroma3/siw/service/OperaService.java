package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepo;
	
	@Transactional
	public Opera save(Opera opera) {
		return operaRepo.save(opera);
	}
	
	@Transactional
	public List<Opera> cercaPerTitolo(String titolo){
		return operaRepo.findByTitolo(titolo);
	}
	
	@Transactional
	public List<Opera> cercaPerAutore(Artista autore){
		return operaRepo.findByAutore(autore);
	}
	
	@Transactional
	public List<Opera> cercaPerAnno(Long anno){
		return operaRepo.findByAnno(anno);
	}
	
	@Transactional
	public List<Opera> cercaPerCollezione(Collezione collezione){
		return operaRepo.findByCollezione(collezione);
	}
	
	@Transactional
	public Opera cercaPerTitoloEAutore(String titolo, Artista autore) {
		return operaRepo.findByTitoloAndAutore(titolo, autore);
	}
	
	@Transactional
	public boolean alreadyExists(Opera opera) {
		if (this.operaRepo.findByTitoloAndAutore(opera.getTitolo(), opera.getAutore()) != null)
			return true;
		else 
			return false;
	}

}
