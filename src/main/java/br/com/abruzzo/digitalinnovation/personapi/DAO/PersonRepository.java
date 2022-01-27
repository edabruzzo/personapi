package br.com.abruzzo.digitalinnovation.personapi.DAO;

import br.com.abruzzo.digitalinnovation.personapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> { }
