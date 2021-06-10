package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.Recensione;
import it.uniroma3.siw.museo.model.User;

@Repository
public interface RecensioneRepository extends CrudRepository<Recensione, Long>{
	
	public List<Recensione> findByUtente(User user);
}
