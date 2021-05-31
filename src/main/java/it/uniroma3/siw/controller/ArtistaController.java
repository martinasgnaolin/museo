package it.uniroma3.siw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.controller.ArtistaValidator;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.model.Artista;

public class ArtistaController {

	@Autowired
	private ArtistaService artistaService;

	@Autowired
	private ArtistaValidator artistaValidator;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/artisti", method = RequestMethod.GET)
	public String getArtisti(Model model) {
		model.addAttribute("artisti", this.artistaService.tutti());
		return "artisti.html";
	}

	@RequestMapping(value = "/artisti/{cognome}", method = RequestMethod.GET)
	public String getArtistiByCognome(@PathVariable("cognome") String cognome, Model model) {
		model.addAttribute("artisti", this.artistaService.cercaPerCognome(cognome));
		return "artisti.html";
	}

	//    @RequestMapping(value="/addArtista", method = RequestMethod.GET)
	//    public String addArtista(Model model) {
	//    	logger.debug("addArtista");
	//    	model.addAttribute("artista", new Artista());
	//        return "artistaForm.html";
	//    }
	//
	//    @RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
	//    public String getArtista(@PathVariable("id") Long id, Model model) {
	//    	model.addAttribute("artista", this.artistaService.artistaPerId(id));
	//    	return "artista.html";
	//    }

	//    @RequestMapping(value = "/persona", method = RequestMethod.POST)
	//    public String newPersona(@ModelAttribute("persona") Persona persona, 
	//    									Model model, BindingResult bindingResult) {
	//    	this.personaValidator.validate(persona, bindingResult);
	//        if (!bindingResult.hasErrors()) {
	//        	this.personaService.inserisci(persona);
	//            model.addAttribute("persone", this.personaService.tutti());
	//            return "persone.html";
	//        }
	//        return "personaForm.html";
	//    }

}
