package br.com.abruzzo.digitalinnovation.personapi.dto;

import br.com.abruzzo.digitalinnovation.personapi.model.Phone;
import lombok.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonDTO {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private String rg;
    private LocalDate birthDate;
    private List<PhoneDTO> phonesDTO = new ArrayList<>();




}
