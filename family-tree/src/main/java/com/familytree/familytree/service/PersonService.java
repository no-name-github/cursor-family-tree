package com.familytree.familytree.service;

import com.familytree.familytree.dto.PersonDTO;
import java.util.List;

/**
 * Service interface for managing family members and their relationships.
 * <p>
 * This service provides methods for creating, updating, retrieving, and deleting
 * family members, as well as managing relationships between them.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
public interface PersonService {
    /**
     * Creates a new person.
     *
     * @param personDTO the person data to create
     * @return the created person
     */
    PersonDTO createNewPerson(PersonDTO personDTO);

    /**
     * Updates an existing person.
     *
     * @param personDTO the person data to update
     * @return the updated person
     */
    PersonDTO updatePerson(PersonDTO personDTO);

    /**
     * Retrieves a person by their ID.
     *
     * @param personId the ID of the person to retrieve
     * @return the person
     */
    PersonDTO getPerson(Long personId);

    /**
     * Finds persons by first name.
     *
     * @param firstName the first name to search for
     * @return a list of persons with the specified first name
     */
    List<PersonDTO> findPersonByFirstName(String firstName);

    /**
     * Finds persons by last name.
     *
     * @param lastName the last name to search for
     * @return a list of persons with the specified last name
     */
    List<PersonDTO> findPersonByLastName(String lastName);

    /**
     * Deletes a person by their ID.
     *
     * @param personId the ID of the person to delete
     */
    void deletePerson(Long personId);
    
    /**
     * Adds a child to a person.
     *
     * @param personId the ID of the parent
     * @param childDTO the child data to create
     * @return the created child
     */
    PersonDTO addChild(Long personId, PersonDTO childDTO);

    /**
     * Adds an existing child to a person.
     *
     * @param personId the ID of the parent
     * @param childId the ID of the existing child
     * @return the updated child
     */
    PersonDTO addChild(Long personId, Long childId);
    
    /**
     * Sets a mother for a person.
     *
     * @param personId the ID of the person
     * @param motherDTO the mother data to create
     * @return the updated person
     */
    PersonDTO setMother(Long personId, PersonDTO motherDTO);

    /**
     * Sets an existing person as a mother.
     *
     * @param personId the ID of the person
     * @param motherId the ID of the existing mother
     * @return the updated person
     */
    PersonDTO setMother(Long personId, Long motherId);

    /**
     * Sets a father for a person.
     *
     * @param personId the ID of the person
     * @param fatherDTO the father data to create
     * @return the updated person
     */
    PersonDTO setFather(Long personId, PersonDTO fatherDTO);

    /**
     * Sets an existing person as a father.
     *
     * @param personId the ID of the person
     * @param fatherId the ID of the existing father
     * @return the updated person
     */
    PersonDTO setFather(Long personId, Long fatherId);
    
    /**
     * Sets a spouse for a person.
     *
     * @param personId the ID of the person
     * @param spouseDTO the spouse data to create
     * @return the updated person
     */
    PersonDTO setSpouse(Long personId, PersonDTO spouseDTO);

    /**
     * Sets an existing person as a spouse.
     *
     * @param personId the ID of the person
     * @param spouseId the ID of the existing spouse
     * @return the updated person
     */
    PersonDTO setSpouse(Long personId, Long spouseId);

    /**
     * Deletes a spouse relationship.
     *
     * @param personId the ID of the person
     */
    void deleteSpouse(Long personId);

    /**
     * Adds a former spouse to a person.
     *
     * @param personId the ID of the person
     * @param formerSpouseDTO the former spouse data to create
     * @return the created former spouse
     */
    PersonDTO addFormerSpouse(Long personId, PersonDTO formerSpouseDTO);

    /**
     * Adds an existing person as a former spouse.
     *
     * @param personId the ID of the person
     * @param formerSpouseId the ID of the existing former spouse
     * @return the former spouse
     */
    PersonDTO addFormerSpouse(Long personId, Long formerSpouseId);
} 