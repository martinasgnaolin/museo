package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Collezione {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String descrizione;
	
	@OneToMany(mappedBy = "collezione")
	private List<Opera> opere;
	
	@ManyToOne
	private Dipendente curatore;

}
