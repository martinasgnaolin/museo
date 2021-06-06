package it.uniroma3.siw.museo.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import it.uniroma3.siw.museo.model.Prenotazione;
import it.uniroma3.siw.museo.service.PrenotazioneService;

@Component
public class PrenotazioneValidator implements Validator{
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	private static final Logger logger = LoggerFactory.getLogger(PrenotazioneValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return Prenotazione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "utente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "visita", "required");
		
		if(!errors.hasErrors()) {
			logger.debug("I valori inseriti sono validi.");
			if(this.prenotazioneService.alreadyExists((Prenotazione)target)) {
				logger.debug("Prenotazione già presente.");
				errors.reject("duplicato");
			}
		}
		
	}

}
