package com.familytree.familytree.service;

import com.familytree.familytree.dto.PersonDTO;
import java.util.List;

public interface PersonService {
    PersonDTO createNewPerson(PersonDTO personDTO);
    PersonDTO updatePerson(PersonDTO personDTO);
    PersonDTO getPerson(Long personId);
    List<PersonDTO> findPersonByFirstName(String firstName);
    List<PersonDTO> findPersonByLastName(String lastName);
    void deletePerson(Long personId);
    
    // Child management
    PersonDTO addChild(Long personId, PersonDTO childDTO);
    PersonDTO addExistingChild(Long personId, Long childId);
    
    // Parent management
    PersonDTO setMother(Long personId, PersonDTO motherDTO);
    PersonDTO setExistingMother(Long personId, Long motherId);
    PersonDTO setFather(Long personId, PersonDTO fatherDTO);
    PersonDTO setExistingFather(Long personId, Long fatherId);
    
    // Spouse management
    PersonDTO setSpouse(Long personId, PersonDTO spouseDTO);
    PersonDTO setExistingSpouse(Long personId, Long spouseId);
    void deleteSpouse(Long personId);
    PersonDTO addFormerSpouse(Long personId, PersonDTO formerSpouseDTO);
} 