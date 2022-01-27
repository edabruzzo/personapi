package br.com.abruzzo.digitalinnovation.personapi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PhoneDTO {

    private UUID uuid;
    private PhoneTypeDTO phoneTypeDTO;
    private String number;


}
