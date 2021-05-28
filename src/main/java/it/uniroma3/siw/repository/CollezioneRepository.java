package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Curatore;

public interface CollezioneRepository extends CrudRepository<Collezione, Long> {

	public List<Collezione> findByNome(String nome);

	public List<Collezione> findByCuratore(Curatore curatore);
	
}
