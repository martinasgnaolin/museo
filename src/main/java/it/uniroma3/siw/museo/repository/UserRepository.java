package it.uniroma3.siw.museo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}