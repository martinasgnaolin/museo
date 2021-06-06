package it.uniroma3.siw.museo.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Visita;

@Repository
public interface VisitaRepository extends CrudRepository<Visita, Long>{

	public List<Visita> findByCollezione(Collezione collezione);

	//public List<Visita> findByPrenotazione(Prenotazione prenotazione);

	public List<Visita> findByDataVisitaAndOraAndCollezione(LocalDate dataVisita,LocalTime ora, Collezione collezione);
	
}
