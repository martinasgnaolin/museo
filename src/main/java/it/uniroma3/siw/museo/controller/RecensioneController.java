package it.uniroma3.siw.museo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.museo.controller.validator.RecensioneValidator;
import it.uniroma3.siw.museo.model.Recensione;
import it.uniroma3.siw.museo.model.User;
import it.uniroma3.siw.museo.service.RecensioneService;

@Controller
public class RecensioneController {

	@Autowired
	private RecensioneValidator recensioneValidator;

	@Autowired
	private RecensioneService recensioneService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/addRecensione", method = RequestMethod.GET)
	public String addRecensione(Model model) {
		logger.debug("addRecensione");
		model.addAttribute("recensione", new Recensione());
		return "recensioneForm.html";
	}

	@RequestMapping(value = "/recensione", method = RequestMethod.POST)
	public String newRecensione(@ModelAttribute("recensione") Recensione recensione, 
			Model model, BindingResult bindingResult, HttpServletRequest request) {
		String username = request.getUserPrincipal().getName();
		User user = this.recensioneService.cercaCredenzialiPerUsername(username).getUser();
		recensione.setUtente(user);
		this.recensioneValidator.validate(recensione, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.recensioneService.save(recensione);
			model.addAttribute("utente", user);
			model.addAttribute("recensioni", 1);
			return "areaPersonale.html";
		}
		return "recensioneForm.html";
	}

	@RequestMapping(value = {"/informazioni"}, method = RequestMethod.GET)
	public String informazioni(Model model) {
		model.addAttribute("recensioni", this.recensioneService.tutti());
		return "informazioni.html";
	}

	@RequestMapping(value="/deleteRecensione", method = RequestMethod.GET)
	public String deleteRecensione(Model model) {
		logger.debug("deleteRecensione");
		model.addAttribute("recensioni", this.recensioneService.tutti());
		return "recensioneDelete.html";
	}

	@RequestMapping(value = "/deleteRecensione", method = RequestMethod.POST)
	public String delete(@RequestParam("recensioneId") Long recensioneId, 
			Model model) {
		Recensione recensione = this.recensioneService.recensionePerId(recensioneId);
		this.recensioneService.delete(recensione);
		return "admin/home";
	}

}
