package it.uniroma3.siw.museo.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.museo.model.Recensione;
import it.uniroma3.siw.museo.service.RecensioneService;


@Component
public class RecensioneValidator implements Validator{

	@Autowired
	private RecensioneService recensioneService;
	
	private static final Logger logger = LoggerFactory.getLogger(RecensioneValidator.class);
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Recensione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "testo", "required");
		
		if(!errors.hasErrors()) {
			logger.debug("I valori inseriti sono validi.");
		}
		
	}

}
