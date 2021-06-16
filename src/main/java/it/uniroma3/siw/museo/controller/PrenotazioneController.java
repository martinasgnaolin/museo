package it.uniroma3.siw.museo.controller;

import javax.servlet.http.HttpServletRequest;

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

import it.uniroma3.siw.museo.controller.validator.PrenotazioneValidator;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Prenotazione;
import it.uniroma3.siw.museo.model.User;
import it.uniroma3.siw.museo.service.PrenotazioneService;

@Controller
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private PrenotazioneValidator prenotazioneValidator;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/prenotazioni", method = RequestMethod.GET)
	public String getPrenotazioni(Model model) {
		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
		return "prenotazione.html";
	}


	@RequestMapping(value = "/prenotazione/{id}", method = RequestMethod.GET)
	public String getPrenotazione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("prenotazione", this.prenotazioneService.prenotazionePerId(id));
		return "prenotazione.html";
	}

	@RequestMapping(value="/addPrenotazione", method = RequestMethod.POST)
	public String addPrenotazione(Model model, @ModelAttribute("collezioneId") Long collezioneId) {
		logger.debug("addPrenotazione");
		model.addAttribute("prenotazione", new Prenotazione());
		Collezione collezione= this.prenotazioneService.cercaCollezionePerId(collezioneId);
		model.addAttribute("visite",collezione.getVisite());
		return "prenotazioneForm.html";
	}

	@RequestMapping(value = "/prenotazione", method = RequestMethod.POST)
	public String newPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, 
			Model model, BindingResult bindingResult, HttpServletRequest request) {
		String username = request.getUserPrincipal().getName();
		User user = this.prenotazioneService.cercaCredenzialiPerUsername(username).getUser();
		prenotazione.setUtente(user);
		this.prenotazioneValidator.validate(prenotazione, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.prenotazioneService.save(prenotazione);
			model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
			model.addAttribute("user", user);
			return "areaPersonale.html";
		}
		model.addAttribute("visite", prenotazione.getVisita().getCollezione().getVisite());
		return "prenotazioneForm.html";
	}

	@RequestMapping(value="/deletePrenotazione", method = RequestMethod.GET)
	public String deletePrenotazione(Model model) {
		logger.debug("deletePrenotazione");
		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
		return "prenotazioneDelete.html";
	}

	@RequestMapping(value = "/deletePrenotazione", method = RequestMethod.POST)
	public String delete(@RequestParam("prenotazioneId") Long prenotazioneId, 
			Model model) {
		Prenotazione prenotazione = this.prenotazioneService.prenotazionePerId(prenotazioneId);
		this.prenotazioneService.delete(prenotazione);
		return "admin/home";
	}

}
