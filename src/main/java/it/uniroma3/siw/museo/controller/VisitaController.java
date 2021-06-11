package it.uniroma3.siw.museo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.museo.controller.validator.VisitaValidator;
import it.uniroma3.siw.museo.model.Visita;
import it.uniroma3.siw.museo.service.VisitaService;

@Controller
public class VisitaController {
	
	@Autowired
	private VisitaService visitaService;

	@Autowired
	private VisitaValidator visitaValidator;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/visite", method = RequestMethod.GET)
	public String getVisite(Model model) {
		model.addAttribute("visite", this.visitaService.tutti());
		return "visite.html";
	}

	@RequestMapping(value = "/visita/{id}", method = RequestMethod.GET)
	public String getVisita(@PathVariable("id") Long id, Model model) {
		model.addAttribute("visita", this.visitaService.visitaPerId(id));
		return "visite.html";
	}
	
	@RequestMapping(value="/addVisita", method = RequestMethod.GET)
	public String addVisita(Model model) {
		logger.debug("addVisita");
		model.addAttribute("visita", new Visita());
		model.addAttribute("collezioni", visitaService.getAllCollezioni());
		return "visitaForm.html";
	}

	@RequestMapping(value = "/visita", method = RequestMethod.POST)
	public String newVisita(@ModelAttribute("visita") Visita visita, 
			Model model, BindingResult bindingResult) {
		this.visitaValidator.validate(visita, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.visitaService.save(visita);
			model.addAttribute("visita", visita);
			return "visita.html";
		}
		return "visitaForm.html";
	}

	@RequestMapping(value="/deleteVisita", method = RequestMethod.GET)
	public String deleteVisita(Model model) {
		logger.debug("deleteVisita");
		model.addAttribute("visita", this.visitaService.tutti());
		return "visitaDelete.html";
	}
	
	@RequestMapping(value = "/deleteVisita", method = RequestMethod.POST)
	public String delete(@RequestParam("visitaId") Long visitaId, 
			Model model) {
		Visita visita = this.visitaService.visitaPerId(visitaId);
		this.visitaService.delete(visita);
		return "admin/home";
	}


}
