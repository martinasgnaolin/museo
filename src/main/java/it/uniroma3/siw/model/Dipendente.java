package it.uniroma3.siw.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Dipendente {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	private Date dataNascita;
	
	private String luogoNascita;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String telefono;
	
	@Column(nullable = false, unique = true)
	private Long matricola; 
	
	@OneToMany(mappedBy = "curatore")
	private List<Collezione> collezioniCurate;

}
