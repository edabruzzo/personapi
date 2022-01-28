package br.com.abruzzo.digitalinnovation.personapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class PersonDTONotFoundException extends HttpStatusCodeException {
    public PersonDTONotFoundException(String mensagemErro) {
        super(HttpStatus.NOT_FOUND, mensagemErro);
    }
}
