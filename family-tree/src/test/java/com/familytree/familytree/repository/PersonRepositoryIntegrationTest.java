package com.familytree.familytree.repository;

import com.familytree.familytree.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setOccupation("Engineer");
        person.setBirthPlace("New York");
        person.setBornDate(LocalDate.of(1980, 1, 1));
        person = entityManager.persist(person);
        entityManager.flush();
    }

    @Test
    void findByFirstNameAndLastName_Success() {
        Optional<Person> found = personRepository.findByFirstNameAndLastName("John", "Doe");
        
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getFirstName());
        assertEquals("Doe", found.get().getLastName());
    }

    @Test
    void findByFirstName_Success() {
        List<Person> found = personRepository.findByFirstName("John");
        
        assertFalse(found.isEmpty());
        assertEquals(1, found.size());
        assertEquals("John", found.get(0).getFirstName());
    }

    @Test
    void findByLastName_Success() {
        List<Person> found = personRepository.findByLastName("Doe");
        
        assertFalse(found.isEmpty());
        assertEquals(1, found.size());
        assertEquals("Doe", found.get(0).getLastName());
    }

    @Test
    void findByBirthPlace_Success() {
        List<Person> found = personRepository.findByBirthPlace("New York");
        
        assertFalse(found.isEmpty());
        assertEquals(1, found.size());
        assertEquals("New York", found.get(0).getBirthPlace());
    }

    @Test
    void findByOccupation_Success() {
        List<Person> found = personRepository.findByOccupation("Engineer");
        
        assertFalse(found.isEmpty());
        assertEquals(1, found.size());
        assertEquals("Engineer", found.get(0).getOccupation());
    }

    @Test
    void findByBornDateBetween_Success() {
        LocalDate startDate = LocalDate.of(1979, 1, 1);
        LocalDate endDate = LocalDate.of(1981, 1, 1);
        List<Person> found = personRepository.findByBornDateBetween(startDate, endDate);
        
        assertFalse(found.isEmpty());
        assertEquals(1, found.size());
        assertEquals(LocalDate.of(1980, 1, 1), found.get(0).getBornDate());
    }

    @Test
    void findByDiedDateIsNull_Success() {
        List<Person> found = personRepository.findByDiedDateIsNull();
        
        assertFalse(found.isEmpty());
        assertEquals(1, found.size());
        assertNull(found.get(0).getDiedDate());
    }

    @Test
    void findByChildrenIsNotEmpty_Success() {
        // Create a child
        Person child = new Person();
        child.setFirstName("Child");
        child.setLastName("Doe");
        child.setParent(person);
        entityManager.persist(child);
        entityManager.flush();

        List<Person> found = personRepository.findByChildrenIsNotEmpty();
        
        assertFalse(found.isEmpty());
        assertEquals(1, found.size());
        assertEquals("John", found.get(0).getFirstName());
    }

    @Test
    void findBySpouseIsNotNull_Success() {
        // Create a spouse
        Person spouse = new Person();
        spouse.setFirstName("Jane");
        spouse.setLastName("Doe");
        person.setSpouse(spouse);
        spouse.setSpouse(person);
        entityManager.persist(spouse);
        entityManager.flush();

        List<Person> found = personRepository.findBySpouseIsNotNull();
        
        assertFalse(found.isEmpty());
        assertEquals(2, found.size()); // Both person and spouse should be found
    }
} 