package com.familytree.familytree.service.impl;

import com.familytree.familytree.dto.PersonDTO;
import com.familytree.familytree.entity.Person;
import com.familytree.familytree.exception.PersonNotFoundException;
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
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
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

    @Override
    public void deletePerson(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        personRepository.delete(person);
    }

    @Override
    public PersonDTO addChild(Long personId, PersonDTO childDTO) {
        Person parent = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Parent not found with id: " + personId));
        Person child = convertToEntity(childDTO);
        child.setParent(parent);
        parent.getChildren().add(child);
        Person savedChild = personRepository.save(child);
        return convertToDTO(savedChild);
    }

    @Override
    public PersonDTO addChild(Long personId, Long childId) {
        Person parent = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Parent not found with id: " + personId));
        Person child = personRepository.findById(childId)
                .orElseThrow(() -> new PersonNotFoundException("Child not found with id: " + childId));
        child.setParent(parent);
        parent.getChildren().add(child);
        Person savedChild = personRepository.save(child);
        return convertToDTO(savedChild);
    }

    @Override
    public PersonDTO setMother(Long personId, PersonDTO motherDTO) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        Person mother = convertToEntity(motherDTO);
        person.setMother(mother);
        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }

    @Override
    public PersonDTO setMother(Long personId, Long motherId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        Person mother = personRepository.findById(motherId)
                .orElseThrow(() -> new PersonNotFoundException("Mother not found with id: " + motherId));
        person.setMother(mother);
        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }

    @Override
    public PersonDTO setFather(Long personId, PersonDTO fatherDTO) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        Person father = convertToEntity(fatherDTO);
        person.setFather(father);
        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }

    @Override
    public PersonDTO setFather(Long personId, Long fatherId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        Person father = personRepository.findById(fatherId)
                .orElseThrow(() -> new PersonNotFoundException("Father not found with id: " + fatherId));
        person.setFather(father);
        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }

    @Override
    public PersonDTO setSpouse(Long personId, PersonDTO spouseDTO) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        Person spouse = convertToEntity(spouseDTO);
        person.setSpouse(spouse);
        spouse.setSpouse(person); // Set bidirectional relationship
        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }

    @Override
    public PersonDTO setSpouse(Long personId, Long spouseId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        Person spouse = personRepository.findById(spouseId)
                .orElseThrow(() -> new PersonNotFoundException("Spouse not found with id: " + spouseId));
        person.setSpouse(spouse);
        spouse.setSpouse(person); // Set bidirectional relationship
        Person savedPerson = personRepository.save(person);
        return convertToDTO(savedPerson);
    }

    @Override
    public void deleteSpouse(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        if (person.getSpouse() != null) {
            person.getSpouse().setSpouse(null); // Remove bidirectional relationship
            person.setSpouse(null);
            personRepository.save(person);
        }
    }

    @Override
    public PersonDTO addFormerSpouse(Long personId, PersonDTO formerSpouseDTO) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        Person formerSpouse = convertToEntity(formerSpouseDTO);
        // Here you might want to add logic to store former spouses in a separate table
        // For now, we'll just create the former spouse as a new person
        Person savedFormerSpouse = personRepository.save(formerSpouse);
        return convertToDTO(savedFormerSpouse);
    }

    @Override
    public PersonDTO addFormerSpouse(Long personId, Long formerSpouseId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + personId));
        Person formerSpouse = personRepository.findById(formerSpouseId)
                .orElseThrow(() -> new PersonNotFoundException("Former spouse not found with id: " + formerSpouseId));
        // Here you might want to add logic to store former spouses in a separate table
        // For now, we'll just return the existing former spouse
        return convertToDTO(formerSpouse);
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
                .build();
    }
} 