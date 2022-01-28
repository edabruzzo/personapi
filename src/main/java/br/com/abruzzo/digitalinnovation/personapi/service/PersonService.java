package br.com.abruzzo.digitalinnovation.personapi.service;


import br.com.abruzzo.digitalinnovation.personapi.DAO.PersonRepository;
import br.com.abruzzo.digitalinnovation.personapi.dto.PersonDTO;
import br.com.abruzzo.digitalinnovation.personapi.model.Person;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;


    public PersonDTO createPerson(PersonDTO personDTO) {

        Person person = convertPersonDTO_ToModel(personDTO);
        person = this.personRepository.save(person);
        personDTO = convertPersonModelToDTO(person);
        return personDTO;
    }

    private Person convertPersonDTO_ToModel(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        return person;
    }

    private PersonDTO convertPersonModelToDTO(Person person) {
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }


    public List<PersonDTO> getAllPersonDTOs() {

        List<Person> personList = this.personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();
        personList.stream().forEach(person ->{
            personDTOList.add(convertPersonModelToDTO(person));
        });

        return personDTOList;
    }


    public PersonDTO save(PersonDTO personDTO) {
        Person person = this.convertPersonDTO_ToModel(personDTO);
        person = this.personRepository.save(person);
        personDTO = this.convertPersonModelToDTO(person);
        return personDTO;

    }

    public PersonDTO findById(UUID uuid) {
        Optional<Person> personDTOOptional = this.personRepository.findById(uuid);
        Person person = personDTOOptional.orElse(new Person());
        PersonDTO personDTO = this.convertPersonModelToDTO(person);
        return personDTO;

    }

    public void deleteById(UUID uuid) {
        this.personRepository.deleteById(uuid);
    }
}