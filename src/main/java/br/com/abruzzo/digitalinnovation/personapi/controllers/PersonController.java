package br.com.abruzzo.digitalinnovation.personapi.controllers;


import br.com.abruzzo.digitalinnovation.personapi.dto.PersonDTO;
import br.com.abruzzo.digitalinnovation.personapi.exceptions.PersonDTONotFoundException;
import br.com.abruzzo.digitalinnovation.personapi.model.Person;
import br.com.abruzzo.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {


    @Autowired
    private PersonService personService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> getAllPersonDTOs() {
        List<PersonDTO> persons = this.personService.getAllPersonDTOs();
        if (persons.isEmpty()) {
            throw new PersonDTONotFoundException("Persons not found");
        }
        return new ResponseEntity<List<PersonDTO>>(persons, HttpStatus.OK);
    }




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO){
        PersonDTO personCreated = this.personService.createPerson(personDTO);
        return ResponseEntity.ok(personCreated);
    }







}
