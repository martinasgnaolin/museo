package it.uniroma3.siw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator{
	
	@Autowired
	private CollezioneService collezioneService;
	
	private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		
		if(!errors.hasErrors()) {
			logger.debug("I valori inseriti sono validi.");
			if(this.collezioneService.alreadyExists((Collezione)target)) {
				logger.debug("Collezione già presente.");
				errors.reject("duplicato");
			}
		}
		
	}

}
