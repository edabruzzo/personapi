package br.com.abruzzo.digitalinnovation.personapi.service;

import br.com.abruzzo.digitalinnovation.personapi.DAO.PersonRepository;
import br.com.abruzzo.digitalinnovation.personapi.dto.PersonDTO;
import br.com.abruzzo.digitalinnovation.personapi.model.Person;
import br.com.abruzzo.digitalinnovation.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.abruzzo.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static br.com.abruzzo.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;


    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSuccessSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personService.createPerson(personDTO)).thenReturn(personDTO);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        assertEquals(expectedSavedPerson.getCpf(), PersonUtils.createFakeDTO().getCpf());
    }




}


