package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.repository.ArtistaRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepo;
	
	@Transactional
	public Artista save(Artista artista) {
		return artistaRepo.save(artista);
	}
	
	@Transactional
	public List<Artista> cercaPerCognome(String cognome) {
		return artistaRepo.findByCognome(cognome);	
	}
	
	@Transactional
	public List<Artista> cercaPerNomeECognome(String nome, String cognome){
		return artistaRepo.findByNomeAndCognome(nome, cognome);		
	}
	
	@Transactional
	public boolean alreadyExists(Artista artista) {
		List<Artista> artisti = this.artistaRepo.findByNomeAndCognome(artista.getNome(), artista.getCognome());
		if (artisti.size() > 0)
			return true;
		else 
			return false;
	}

	

}
