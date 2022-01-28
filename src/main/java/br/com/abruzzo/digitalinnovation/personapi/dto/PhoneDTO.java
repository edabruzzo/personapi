package br.com.abruzzo.digitalinnovation.personapi.dto;

import br.com.abruzzo.digitalinnovation.personapi.model.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private PhoneType phoneTypeDTO;

    @NotEmpty
    @Size(min=10,max=14)
    private String number;


}
