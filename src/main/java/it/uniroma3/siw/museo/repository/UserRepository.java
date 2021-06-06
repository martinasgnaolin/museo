package it.uniroma3.siw.museo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByNome(String nome);

}