package com.familytree.familytree.service.impl;

import com.familytree.familytree.dto.PersonDTO;
import com.familytree.familytree.entity.Person;
import com.familytree.familytree.repository.PersonRepository;
import com.familytree.familytree.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDTO createNewPerson(PersonDTO personDTO) {
        Person person = convertToEntity(personDTO);
        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO) {
        if (personDTO.getId() == null) {
            throw new IllegalArgumentException("Person ID cannot be null for update");
        }
        Person person = convertToEntity(personDTO);
        Person updatedPerson = personRepository.save(person);
        return convertToDTO(updatedPerson);
    }

    @Override
    public PersonDTO getPerson(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + personId));
        return convertToDTO(person);
    }

    @Override
    public List<PersonDTO> findPersonByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonDTO> findPersonByLastName(String lastName) {
        return personRepository.findByLastName(lastName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Person convertToEntity(PersonDTO dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setMiddleName(dto.getMiddleName());
        person.setLastName(dto.getLastName());
        person.setOccupation(dto.getOccupation());
        person.setLifeStory(dto.getLifeStory());
        person.setBornDate(dto.getBornDate());
        person.setDiedDate(dto.getDiedDate());
        person.setBirthPlace(dto.getBirthPlace());
        person.setCurrentlyLivesAtAddress(dto.getCurrentlyLivesAtAddress());
        
        // Handle relationships if IDs are provided
        if (dto.getMotherId() != null) {
            person.setMother(personRepository.findById(dto.getMotherId()).orElse(null));
        }
        if (dto.getFatherId() != null) {
            person.setFather(personRepository.findById(dto.getFatherId()).orElse(null));
        }
        if (dto.getSpouseId() != null) {
            person.setSpouse(personRepository.findById(dto.getSpouseId()).orElse(null));
        }
        if (dto.getParentId() != null) {
            person.setParent(personRepository.findById(dto.getParentId()).orElse(null));
        }
        
        return person;
    }

    private PersonDTO convertToDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .middleName(person.getMiddleName())
                .lastName(person.getLastName())
                .occupation(person.getOccupation())
                .lifeStory(person.getLifeStory())
                .bornDate(person.getBornDate())
                .diedDate(person.getDiedDate())
                .birthPlace(person.getBirthPlace())
                .currentlyLivesAtAddress(person.getCurrentlyLivesAtAddress())
                .motherId(person.getMother() != null ? person.getMother().getId() : null)
                .fatherId(person.getFather() != null ? person.getFather().getId() : null)
                .spouseId(person.getSpouse() != null ? person.getSpouse().getId() : null)
                .parentId(person.getParent() != null ? person.getParent().getId() : null)
                .build();
    }
} 