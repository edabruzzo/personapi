package br.com.abruzzo.digitalinnovation.personapi.service;


import br.com.abruzzo.digitalinnovation.personapi.DAO.PersonRepository;
import br.com.abruzzo.digitalinnovation.personapi.dto.PersonDTO;
import br.com.abruzzo.digitalinnovation.personapi.model.Person;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;


    public PersonDTO createPerson(PersonDTO personDTO) {

        Person person = modelMapper.map(personDTO, Person.class);
        person = this.personRepository.save(person);
        personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }


}