package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Prenotazione;
import it.uniroma3.siw.museo.model.Visita;
import it.uniroma3.siw.museo.repository.VisitaRepository;

@Service
public class VisitaService {

	@Autowired
	private VisitaRepository visitaRepo;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Transactional
	public Visita save(Visita visita) {
		return visitaRepo.save(visita);
	}
	
	@Transactional
	public List<Visita> cercaPerCollezione(Collezione collezione){
		return visitaRepo.findByCollezione(collezione);
	}

	@Transactional
	public List<Visita> tutti() {
		return (List<Visita>) visitaRepo.findAll();
	}

	@Transactional
	public Visita visitaPerId(Long id) {
		Optional<Visita> optional = visitaRepo.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public boolean alreadyExists(Visita visita) {
		List<Visita> visite = this.visitaRepo.findByDataVisitaAndOraAndCollezione(visita.getDataVisita(),visita.getOra(), visita.getCollezione());
		if (visite.size() > 0)
			return true;
		else 
			return false;
	}

	public List<Collezione> getAllCollezioni() {
		return this.collezioneService.tutti();
	}
}
