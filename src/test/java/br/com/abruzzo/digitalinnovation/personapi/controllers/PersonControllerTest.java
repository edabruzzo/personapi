package br.com.abruzzo.digitalinnovation.PersonDTOapi.controllers;

import br.com.abruzzo.digitalinnovation.personapi.controllers.PersonController;
import br.com.abruzzo.digitalinnovation.personapi.dto.PersonDTO;
import br.com.abruzzo.digitalinnovation.personapi.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
@Import(PersonService.class)
class PersonDTOControllerTest {


    private static final String API_BASE = "/api/v1/persons";

    @Mock
    private RestTemplate restTemplate;

    @MockBean
    PersonService personService;

    @InjectMocks
    private PersonController personController;


    @Autowired
    WebTestClient webTestClient;



    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    List<PersonDTO> personDTOList = new ArrayList<>();
    PersonDTO personDTO1;
    PersonDTO personDTO2;



    @BeforeEach
    public void setup() throws Exception {
        System.out.println("@Beforeeach was called!");
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .alwaysExpect(content().contentType("application/json;charset=UTF-8"))
                .build();

        personDTO1 = new PersonDTO();
        personDTO1.setFirstName("Maria");
        personDTO1.setLastName("Silva");
        personDTO1.setBirthDate(LocalDate.parse("1983-03-03"));
        personDTO1.setCpf("333.333.333-33");
        personDTO1.setRg("33.333.333-3");
        personDTO1.setEmail("mariasilva@gmail.com");
        personDTO1.setUuid(UUID.fromString(personDTO1.getFirstName()));

        personDTO2 = new PersonDTO();
        personDTO2.setFirstName("João");
        personDTO2.setLastName("dos Santos");
        personDTO2.setBirthDate(LocalDate.parse("1984-04-04"));
        personDTO2.setCpf("444.444.444-44");
        personDTO2.setRg("44.444.444-4");
        personDTO2.setEmail("joaosantos@gmail.com");
        personDTO2.setUuid(UUID.fromString(personDTO2.getFirstName()));
        personDTOList.add(personDTO1);
        personDTOList.add(personDTO2);



    }



    @Test
    void whenCallPOSTRequest_saveNewPersonOnDatabase() throws Exception {

        Mockito.when(personService.save(Mockito.any(PersonDTO.class))).thenReturn(this.personDTO1);

        mockMvc.perform(MockMvcRequestBuilders.post(String.format("%s",API_BASE))
                .content(asJsonString(this.personDTO1))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }


    @Test
    void whenCallGETMethod_findAllPersons() {
        Mockito.when(restTemplate.getForEntity(API_BASE, List.class))
                .thenReturn(new ResponseEntity(this.personDTOList, HttpStatus.OK));
        assertThat(this.personDTOList.size()).isEqualTo(4);
    }


    @Test
    void whenCallGETMethodById_findOnePerson() {

        this.webTestClient.get()
                .uri(String.format("%s/%s",API_BASE,UUID.fromString(this.personDTO1.getFirstName())))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PersonDTO.class);

        Mockito.verify(personService, Mockito.times(1)).findById(UUID.fromString(this.personDTO1.getFirstName()));

    }



    @Test
    public void testDelete() throws Exception {

        PersonService spy = Mockito.spy(this.personService);
        Mockito.doNothing().when(spy).deleteById(UUID.fromString(this.personDTO1.getFirstName()));
        webTestClient.delete()
                .uri(String.format("%s/%s",API_BASE,UUID.fromString(this.personDTO1.getFirstName())))
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    void whenCallUpdateById_AssertThatPersonisUpdated() throws Exception {

        Mockito.when(personService.save(Mockito.any(PersonDTO.class))).thenReturn(this.personDTO1);

        mockMvc.perform(MockMvcRequestBuilders.post(String.format("%s/%s",API_BASE,UUID.fromString(this.personDTO1.getFirstName())))
                        .content(asJsonString(this.personDTO1))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }


}




