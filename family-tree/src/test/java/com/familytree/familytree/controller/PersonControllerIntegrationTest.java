package com.familytree.familytree.controller;

import com.familytree.familytree.dto.PersonDTO;
import com.familytree.familytree.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    @Test
    void createPerson_Success() throws Exception {
        String personJson = """
            {
                "firstName": "John",
                "lastName": "Doe",
                "occupation": "Engineer",
                "birthPlace": "New York",
                "bornDate": "1980-01-01"
            }
            """;

        MvcResult result = mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertTrue(response.contains("John"));
        assertTrue(response.contains("Doe"));
    }

    @Test
    void getPerson_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Jane")
                .lastName("Doe")
                .occupation("Doctor")
                .birthPlace("Boston")
                .bornDate(LocalDate.of(1985, 5, 15))
                .build();
        PersonDTO createdPerson = personService.createNewPerson(personDTO);

        // Then get the person
        mockMvc.perform(get("/api/person/" + createdPerson.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void getPerson_NotFound() throws Exception {
        mockMvc.perform(get("/api/person/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findPersonByFirstName_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Alice")
                .lastName("Smith")
                .occupation("Teacher")
                .birthPlace("Chicago")
                .bornDate(LocalDate.of(1990, 3, 20))
                .build();
        personService.createNewPerson(personDTO);

        // Then search by first name
        mockMvc.perform(get("/api/person/first-name/Alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Alice"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"));
    }

    @Test
    void findPersonByLastName_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Bob")
                .lastName("Johnson")
                .occupation("Lawyer")
                .birthPlace("Los Angeles")
                .bornDate(LocalDate.of(1975, 8, 10))
                .build();
        personService.createNewPerson(personDTO);

        // Then search by last name
        mockMvc.perform(get("/api/person/last-name/Johnson"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Bob"))
                .andExpect(jsonPath("$[0].lastName").value("Johnson"));
    }

    @Test
    void deletePerson_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Charlie")
                .lastName("Brown")
                .occupation("Artist")
                .birthPlace("Seattle")
                .bornDate(LocalDate.of(1988, 12, 25))
                .build();
        PersonDTO createdPerson = personService.createNewPerson(personDTO);

        // Then delete the person
        mockMvc.perform(delete("/api/person/" + createdPerson.getId()))
                .andExpect(status().isNoContent());

        // Verify the person is deleted
        mockMvc.perform(get("/api/person/" + createdPerson.getId()))
                .andExpect(status().isNotFound());
    }
} 