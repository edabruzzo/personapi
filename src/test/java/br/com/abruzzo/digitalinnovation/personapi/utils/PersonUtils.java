package br.com.abruzzo.digitalinnovation.personapi.utils;

import br.com.abruzzo.digitalinnovation.personapi.dto.PersonDTO;
import br.com.abruzzo.digitalinnovation.personapi.model.Person;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

public class PersonUtils {

    private static final String FIRST_NAME = "Rodrigo";
    private static final String LAST_NAME = "Peleias";
    private static final String CPF_NUMBER = "369.333.878-79";
    private static final UUID PERSON_ID = UUID.fromString(CPF_NUMBER);
    public static final LocalDate BIRTH_DATE = LocalDate.of(2010, 10, 1);

    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(LocalDate.parse("04-04-2010"))
                .phonesDTO(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .uuid(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }




}