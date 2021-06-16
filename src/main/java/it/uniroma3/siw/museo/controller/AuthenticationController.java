package it.uniroma3.siw.museo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.controller.validator.CredentialsValidator;
import it.uniroma3.siw.museo.controller.validator.UserValidator;
import it.uniroma3.siw.museo.model.Credentials;
import it.uniroma3.siw.museo.model.User;
import it.uniroma3.siw.museo.service.CredentialsService;

@Controller
public class AuthenticationController {

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private CredentialsValidator credentialsValidator;

	@RequestMapping(value = "/autenticazione", method = RequestMethod.GET) 
	public String loginOrRegister (Model model, HttpServletRequest request) {
		if(request.getUserPrincipal()!=null) {
			String username = request.getUserPrincipal().getName();
			Credentials credentials = this.credentialsService.getCredentials(username);
			model.addAttribute("utente",  credentials.getUser());
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				return "admin/home";
			}
			else { 
				if(credentials.getUser().getVisitePrenotate().size() == 0) {
					model.addAttribute("prenotazioni", null);
				} else { model.addAttribute("prenotazioni", credentials.getUser().getVisitePrenotate()); }

				if(credentials.getUser().getRecensioni().size() > 0) {
					model.addAttribute("recensioni", 1); }

				return "areaPersonale.html";
			}
		}
		return "accediRegistrati.html";

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("utente", new User());
		model.addAttribute("credentials", new Credentials());
		return "registerUser";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "loginForm";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultAfterLogin(Model model, HttpServletRequest request) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());

		String username = request.getUserPrincipal().getName();
		model.addAttribute("utente", this.credentialsService.getCredentials(username).getUser());

		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "admin/home";
		}
		else { 
			if(credentials.getUser().getVisitePrenotate().size() == 0) {
				model.addAttribute("prenotazioni", null);
			} else { model.addAttribute("prenotazioni", credentials.getUser().getVisitePrenotate()); }

			if(credentials.getUser().getRecensioni().size() > 0) {
				model.addAttribute("recensioni", true); }

			return "areaPersonale.html";
		}
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("utente") User user,
			BindingResult userBindingResult,
			@ModelAttribute("credentials") Credentials credentials,
			BindingResult credentialsBindingResult,
			Model model) {

		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);

		if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {

			credentials.setUser(user);
			credentialsService.saveCredentials(credentials);
			return "registrationSuccessful"; 
		}
		return "registerUser";
	}

	public Boolean isUtenteLoggato() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(getClass())) {
			return false;
		}
		return auth.isAuthenticated();
	}

}
