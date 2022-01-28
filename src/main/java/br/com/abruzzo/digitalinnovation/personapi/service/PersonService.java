package br.com.abruzzo.digitalinnovation.personapi.service;


import br.com.abruzzo.digitalinnovation.personapi.DAO.PersonRepository;
import br.com.abruzzo.digitalinnovation.personapi.dto.PersonDTO;
import br.com.abruzzo.digitalinnovation.personapi.exceptions.PersonDTONotFoundException;
import br.com.abruzzo.digitalinnovation.personapi.model.Person;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

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
        return personList.stream().map(this::convertPersonModelToDTO).collect(Collectors.toList());
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

    public PersonDTO updatePersonDTO(UUID uuid, PersonDTO personDTO) {
        Person person = this.convertPersonDTO_ToModel(personDTO);

        person = this.personRepository.getById(uuid);

        if(person == null)
                throw new PersonDTONotFoundException(String.format("Não foi encontrada a pessoa com id %s", uuid));
        else{
            person = this.personRepository.save(person);
            personDTO = this.convertPersonModelToDTO(person);
        }
        return personDTO;
    }
}