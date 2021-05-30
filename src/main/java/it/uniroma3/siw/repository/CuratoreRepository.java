package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Curatore;

public interface CuratoreRepository extends CrudRepository<Curatore, Long> {

	public List<Curatore> findByCognome(String cognome);

	public List<Curatore> findByNomeAndCognome(String nome, String cognome);

	public List<Curatore> findByNomeOrCognome(String nome, String cognome);
	
	public Curatore findByEmail(String email);
	
	public Curatore findByMatricola(Long matricola);	
	
}
