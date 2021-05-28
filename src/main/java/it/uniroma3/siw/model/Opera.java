package it.uniroma3.siw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Opera {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String titolo;
	
	private String anno;
	
	private String descrizione;
	
	@ManyToOne
	private Artista autore;
	
	@ManyToOne
	private Collezione collezione;

}
