package com.familytree.familytree.service;

import com.familytree.familytree.dto.PersonDTO;
import java.util.List;

public interface PersonService {
    PersonDTO createNewPerson(PersonDTO personDTO);
    PersonDTO updatePerson(PersonDTO personDTO);
    PersonDTO getPerson(Long personId);
    List<PersonDTO> findPersonByFirstName(String firstName);
    List<PersonDTO> findPersonByLastName(String lastName);
} 