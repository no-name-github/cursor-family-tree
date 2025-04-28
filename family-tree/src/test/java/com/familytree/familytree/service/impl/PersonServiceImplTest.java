package com.familytree.familytree.service.impl;

import com.familytree.familytree.dto.PersonDTO;
import com.familytree.familytree.entity.Person;
import com.familytree.familytree.exception.PersonNotFoundException;
import com.familytree.familytree.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;
    private PersonDTO personDTO;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setOccupation("Engineer");
        person.setBirthPlace("New York");
        person.setBornDate(LocalDate.of(1980, 1, 1));

        personDTO = PersonDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .occupation("Engineer")
                .birthPlace("New York")
                .bornDate(LocalDate.of(1980, 1, 1))
                .build();
    }

    @Test
    void createNewPerson_Success() {
        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonDTO result = personService.createNewPerson(personDTO);

        assertNotNull(result);
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void getPerson_Success() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        PersonDTO result = personService.getPerson(1L);

        assertNotNull(result);
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void getPerson_NotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.getPerson(1L));
        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void findPersonByFirstName_Success() {
        when(personRepository.findByFirstName("John")).thenReturn(Arrays.asList(person));

        List<PersonDTO> results = personService.findPersonByFirstName("John");

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("John", results.get(0).getFirstName());
        verify(personRepository, times(1)).findByFirstName("John");
    }

    @Test
    void findPersonByLastName_Success() {
        when(personRepository.findByLastName("Doe")).thenReturn(Arrays.asList(person));

        List<PersonDTO> results = personService.findPersonByLastName("Doe");

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Doe", results.get(0).getLastName());
        verify(personRepository, times(1)).findByLastName("Doe");
    }

    @Test
    void deletePerson_Success() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        doNothing().when(personRepository).delete(person);

        personService.deletePerson(1L);

        verify(personRepository, times(1)).findById(1L);
        verify(personRepository, times(1)).delete(person);
    }

    @Test
    void deletePerson_NotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.deletePerson(1L));
        verify(personRepository, times(1)).findById(1L);
        verify(personRepository, never()).delete(any(Person.class));
    }
} 