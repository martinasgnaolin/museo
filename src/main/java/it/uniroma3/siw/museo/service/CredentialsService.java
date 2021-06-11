package it.uniroma3.siw.museo.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.museo.model.Credentials;
import it.uniroma3.siw.museo.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
	@org.springframework.beans.factory.annotation.Autowired(required=true)
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialsRepository credentialsRepository;
	
	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }
}
