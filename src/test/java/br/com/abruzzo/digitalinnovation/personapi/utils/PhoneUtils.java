package br.com.abruzzo.digitalinnovation.personapi.utils;


import br.com.abruzzo.digitalinnovation.personapi.dto.PhoneDTO;
import br.com.abruzzo.digitalinnovation.personapi.model.Phone;
import br.com.abruzzo.digitalinnovation.personapi.model.PhoneType;

import java.util.UUID;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "1199999-9999";
    private static final PhoneType PHONE_TYPE = PhoneType.CELLPHONE;
    private static final UUID PHONE_ID = UUID.fromString(PHONE_NUMBER);

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .phoneTypeDTO(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .uuid(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}