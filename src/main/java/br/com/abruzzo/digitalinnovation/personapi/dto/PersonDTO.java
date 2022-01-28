package br.com.abruzzo.digitalinnovation.personapi.dto;

import br.com.abruzzo.digitalinnovation.personapi.model.Phone;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private UUID uuid;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String email;

    @NonNull
    @Column(unique = true)
    private String cpf;

    @NonNull
    @Column(unique = true)
    private String rg;

    private LocalDate birthDate;

    private List<PhoneDTO> phonesDTO = new ArrayList<>();




}
