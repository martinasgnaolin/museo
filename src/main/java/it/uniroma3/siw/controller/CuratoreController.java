package it.uniroma3.siw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.service.CuratoreService;
import it.uniroma3.siw.model.Curatore;

public class CuratoreController {

	@Autowired
	private CuratoreService curatoreService;

	@Autowired
	private CuratoreValidator curatoreValidator;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/addCuratore", method = RequestMethod.GET)
	public String addCuratore(Model model) {
		logger.debug("addCuratore");
		model.addAttribute("curatore", new Curatore());
		return "curatoreForm.html";
	}

	@RequestMapping(value = "/curatore", method = RequestMethod.POST)
	public String newCuratore(@ModelAttribute("curatore") Curatore curatore, 
			Model model, BindingResult bindingResult) {
		this.curatoreValidator.validate(curatore, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.curatoreService.save(curatore);
			return "index.html";
		}
		return "curatoreForm.html";
	}

}
