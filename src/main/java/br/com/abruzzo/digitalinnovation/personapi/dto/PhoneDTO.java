package br.com.abruzzo.digitalinnovation.personapi.dto;

import br.com.abruzzo.digitalinnovation.personapi.model.PhoneType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

public class PhoneDTO {

    private UUID uuid;
    private PhoneTypeDTO phoneTypeDTO;
    private String number;


}
