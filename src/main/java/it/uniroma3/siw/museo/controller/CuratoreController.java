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

import it.uniroma3.siw.museo.controller.validator.CuratoreValidator;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.service.CuratoreService;

@Controller
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
	
	@RequestMapping(value = "/curatore/{id}", method = RequestMethod.GET)
	public String getCuratore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("curatore", this.curatoreService.curatorePerId(id));
		return "curatore.html";
	}


	@RequestMapping(value = "/curatore", method = RequestMethod.POST)
	public String newCuratore(@ModelAttribute("curatore") Curatore curatore, 
			Model model, BindingResult bindingResult) {
		this.curatoreValidator.validate(curatore, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.curatoreService.save(curatore);
			model.addAttribute("curatore", curatore);
			return "curatore.html";
		}
		return "curatoreForm.html";
	}

}
