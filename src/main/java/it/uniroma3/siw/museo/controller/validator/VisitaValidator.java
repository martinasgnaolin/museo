package it.uniroma3.siw.museo.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import it.uniroma3.siw.museo.model.Visita;
import it.uniroma3.siw.museo.service.VisitaService;

@Component
public class VisitaValidator implements Validator{
	
	@Autowired
	private VisitaService visitaService;
	
	private static final Logger logger = LoggerFactory.getLogger(VisitaValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return Visita.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "collezione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataVisita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ora", "required");
		
		if(!errors.hasErrors()) {
			logger.debug("I valori inseriti sono validi.");
			if(this.visitaService.alreadyExists((Visita)target)) {
				logger.debug("Visita già presente.");
				errors.reject("duplicato");
			}
		}
		
	}


}
