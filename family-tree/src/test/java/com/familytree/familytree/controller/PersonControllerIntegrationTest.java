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

    @Test
    void addChild_Success() throws Exception {
        // First create a parent
        PersonDTO parentDTO = PersonDTO.builder()
                .firstName("Parent")
                .lastName("Smith")
                .occupation("Manager")
                .birthPlace("London")
                .bornDate(LocalDate.of(1960, 1, 1))
                .build();
        PersonDTO createdParent = personService.createNewPerson(parentDTO);

        // Create child DTO
        String childJson = """
            {
                "firstName": "Child",
                "lastName": "Smith",
                "occupation": "Student",
                "birthPlace": "London",
                "bornDate": "2000-01-01"
            }
            """;

        // Add child to parent
        mockMvc.perform(post("/api/person/" + createdParent.getId() + "/child")
                .contentType(MediaType.APPLICATION_JSON)
                .content(childJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Child"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    void setMother_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Child")
                .lastName("Smith")
                .occupation("Student")
                .birthPlace("London")
                .bornDate(LocalDate.of(2000, 1, 1))
                .build();
        PersonDTO createdPerson = personService.createNewPerson(personDTO);

        // Create and save mother first
        PersonDTO motherDTO = PersonDTO.builder()
                .firstName("Mother")
                .lastName("Smith")
                .occupation("Teacher")
                .birthPlace("Paris")
                .bornDate(LocalDate.of(1970, 1, 1))
                .build();
        PersonDTO createdMother = personService.createNewPerson(motherDTO);

        // Set mother using the saved mother's ID
        mockMvc.perform(post("/api/person/" + createdPerson.getId() + "/mother/" + createdMother.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.motherId").value(createdMother.getId()));
    }

    @Test
    void setFather_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Child")
                .lastName("Smith")
                .occupation("Student")
                .birthPlace("London")
                .bornDate(LocalDate.of(2000, 1, 1))
                .build();
        PersonDTO createdPerson = personService.createNewPerson(personDTO);

        // Create and save father first
        PersonDTO fatherDTO = PersonDTO.builder()
                .firstName("Father")
                .lastName("Smith")
                .occupation("Engineer")
                .birthPlace("Berlin")
                .bornDate(LocalDate.of(1965, 1, 1))
                .build();
        PersonDTO createdFather = personService.createNewPerson(fatherDTO);

        // Set father using the saved father's ID
        mockMvc.perform(post("/api/person/" + createdPerson.getId() + "/father/" + createdFather.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fatherId").value(createdFather.getId()));
    }

    @Test
    void setSpouse_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Husband")
                .lastName("Smith")
                .occupation("Doctor")
                .birthPlace("Tokyo")
                .bornDate(LocalDate.of(1980, 1, 1))
                .build();
        PersonDTO createdPerson = personService.createNewPerson(personDTO);

        // Create and save spouse first
        PersonDTO spouseDTO = PersonDTO.builder()
                .firstName("Wife")
                .lastName("Smith")
                .occupation("Lawyer")
                .birthPlace("Seoul")
                .bornDate(LocalDate.of(1982, 1, 1))
                .build();
        PersonDTO createdSpouse = personService.createNewPerson(spouseDTO);

        // Set spouse using the saved spouse's ID
        mockMvc.perform(post("/api/person/" + createdPerson.getId() + "/spouse/" + createdSpouse.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.spouseId").value(createdSpouse.getId()));
    }

    @Test
    void deleteSpouse_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Husband")
                .lastName("Smith")
                .occupation("Doctor")
                .birthPlace("Tokyo")
                .bornDate(LocalDate.of(1980, 1, 1))
                .build();
        PersonDTO createdPerson = personService.createNewPerson(personDTO);

        // Create and save spouse first
        PersonDTO spouseDTO = PersonDTO.builder()
                .firstName("Wife")
                .lastName("Smith")
                .occupation("Lawyer")
                .birthPlace("Seoul")
                .bornDate(LocalDate.of(1982, 1, 1))
                .build();
        PersonDTO createdSpouse = personService.createNewPerson(spouseDTO);

        // Set spouse relationship
        personService.setSpouse(createdPerson.getId(), createdSpouse.getId());

        // Delete spouse
        mockMvc.perform(delete("/api/person/" + createdPerson.getId() + "/spouse"))
                .andExpect(status().isNoContent());

        // Verify spouse is deleted
        mockMvc.perform(get("/api/person/" + createdPerson.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.spouseId").doesNotExist());
    }

    @Test
    void addFormerSpouse_Success() throws Exception {
        // First create a person
        PersonDTO personDTO = PersonDTO.builder()
                .firstName("Person")
                .lastName("Smith")
                .occupation("Engineer")
                .birthPlace("Moscow")
                .bornDate(LocalDate.of(1985, 1, 1))
                .build();
        PersonDTO createdPerson = personService.createNewPerson(personDTO);

        // Create former spouse DTO
        String formerSpouseJson = """
            {
                "firstName": "Former",
                "lastName": "Smith",
                "occupation": "Artist",
                "birthPlace": "Rome",
                "bornDate": "1987-01-01"
            }
            """;

        // Add former spouse
        mockMvc.perform(post("/api/person/" + createdPerson.getId() + "/former-spouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(formerSpouseJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Former"))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }
} 