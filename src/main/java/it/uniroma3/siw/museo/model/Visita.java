package it.uniroma3.siw.museo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Visita {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy="visita", cascade = {CascadeType.REMOVE})
	private List<Prenotazione> prenotazioni;


	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVisita;

	@DateTimeFormat(pattern="HH:mm")
	private LocalTime ora;

	@ManyToOne
	private Collezione collezione;

	public Visita() {
		this.prenotazioni=new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public LocalDate getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(LocalDate dataVisita) {
		this.dataVisita = dataVisita;
	}

	public Collezione getCollezione() {
		return collezione;
	}

	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora=ora;
	}





}
