package it.uniroma3.siw.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Artista {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String surname;
	
	private Date dataNascita;
	
	private Date dataMorte;
	
	private String luogoNascita;
	
	private String luogoMorto;
	
	private String nazionalità;
	
	@OneToMany(mappedBy = "autore")
	private List<Opera> opere;
	
	
}