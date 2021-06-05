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
			model.addAttribute("user",  credentials.getUser());
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				return "admin/home";
			}
			else return "areaPersonale.html";
		}
		return "accediRegistrati.html";
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "registerUser";
	}
	
	/*non vengono ritornati dati in quanto vengono gestiti dalla form*/
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
 
    	//springSecurity ci mette a disposizione i dati dell'utente con l'ogg UserDetails, us, psw,ruolo
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	//recuperiamo le credenziali associate a questo user a partire dal nome username
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	
    	//se queste credenziali hanno il ruolo di admin allora mandiamo ad una pagina che contiene il menu dell'amministratore
    	//altrimenti lo mandiamo alla pagina home
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    		String username = request.getUserPrincipal().getName();
			model.addAttribute("user", this.credentialsService.getCredentials(username).getUser());
            return "admin/home";
        }
        return "index";
    }
	
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "registrationSuccessful";  //li mandiamo se è registrato con successo
        }
        return "registerUser";		//altrimenti rimandiamo alla form
    }
    
    public Boolean isUtenteLoggato() {
    	  final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	  if(auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(getClass())) {
    		  return false;
    	  }
    	  return auth.isAuthenticated();
    	}

}
