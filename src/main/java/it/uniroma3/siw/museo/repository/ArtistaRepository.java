package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Artista;

public interface ArtistaRepository extends CrudRepository<Artista, Long> {

	public List<Artista> findByCognome(String cognome);

	public List<Artista> findByNomeAndCognome(String nome, String cognome);
	
}
