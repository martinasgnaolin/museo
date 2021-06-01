package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;

public interface OperaRepository extends CrudRepository<Opera, Long> {

	public List<Opera> findByTitolo(String titolo);

	public List<Opera> findByAutore(Artista autore);
	
	public List<Opera> findByAnno(Long anno);
	
	public List<Opera> findByCollezione(Collezione collezione);	
	
	public Opera findByTitoloAndAutore(String titolo, Artista autore);
	
}
