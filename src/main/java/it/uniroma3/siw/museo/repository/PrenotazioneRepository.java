package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.Prenotazione;
import it.uniroma3.siw.museo.model.User;
import it.uniroma3.siw.museo.model.Visita;

@Repository
public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{

	public List<Prenotazione> findByUtente(User utente);

	public List<Prenotazione> findByVisita(Visita visita);

	public List<Prenotazione> findByUtenteAndVisita(User utente, Visita visita);
	
}
