package br.com.abruzzo.digitalinnovation.personapi.controllers;


import br.com.abruzzo.digitalinnovation.personapi.dto.PersonDTO;
import br.com.abruzzo.digitalinnovation.personapi.exceptions.PersonDTONotFoundException;
import br.com.abruzzo.digitalinnovation.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/persons")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {


    private PersonService personService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> getAllPersonDTOs() {
        List<PersonDTO> persons = this.personService.getAllPersonDTOs();
        if (persons.isEmpty()) {
            throw new PersonDTONotFoundException("Persons not found");
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }



    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> getPersonByUUID(@PathVariable(value = "uuid") UUID uuid) {
        PersonDTO personToUpdate = this.personService.findById(uuid);
        return new ResponseEntity<>(personToUpdate, HttpStatus.OK);
    }


    @PutMapping(value = "/update/person/{uuid}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable(value = "uuid") UUID uuid,
                                                 @Valid @RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(this.personService.updatePersonDTO(uuid, personDTO),
                                             HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/person/{uuid}")
    public ResponseEntity<Object> deletePersonDTO(@PathVariable(value = "uuid") UUID uuid) {
        this.personService.deleteById(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO){
        PersonDTO personCreated = this.personService.createPerson(personDTO);
        return ResponseEntity.ok(personCreated);
    }







}
