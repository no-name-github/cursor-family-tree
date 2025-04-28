package com.familytree.familytree.controller;

import com.familytree.familytree.dto.PersonDTO;
import com.familytree.familytree.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing family members and their relationships.
 * <p>
 * This controller provides endpoints for creating, updating, retrieving, and deleting
 * family members, as well as managing relationships between them.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    /**
     * Constructs a new PersonController with the specified PersonService.
     *
     * @param personService the service to be used for person operations
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Creates a new person.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person \
     * -H "Content-Type: application/json" \
     * -d '{
     *     "firstName": "John",
     *     "lastName": "Doe",
     *     "occupation": "Engineer",
     *     "birthPlace": "New York",
     *     "bornDate": "1980-01-01"
     * }'
     * </pre>
     * </p>
     *
     * @param personDTO the person data to create
     * @return the created person with HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.createNewPerson(personDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing person.
     * <p>
     * Example:
     * <pre>
     * curl -X PUT http://localhost:8080/api/person \
     * -H "Content-Type: application/json" \
     * -d '{
     *     "id": 1,
     *     "firstName": "John",
     *     "lastName": "Doe",
     *     "occupation": "Senior Engineer",
     *     "birthPlace": "New York",
     *     "bornDate": "1980-01-01"
     * }'
     * </pre>
     * </p>
     *
     * @param personDTO the person data to update
     * @return the updated person with HTTP status 200 (OK)
     */
    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.updatePerson(personDTO));
    }

    /**
     * Retrieves a person by their ID.
     * <p>
     * Example:
     * <pre>
     * curl -X GET http://localhost:8080/api/person/1
     * </pre>
     * </p>
     *
     * @param personId the ID of the person to retrieve
     * @return the person with HTTP status 200 (OK)
     */
    @GetMapping("/{personId}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long personId) {
        return ResponseEntity.ok(personService.getPerson(personId));
    }

    /**
     * Deletes a person by their ID.
     * <p>
     * Example:
     * <pre>
     * curl -X DELETE http://localhost:8080/api/person/1
     * </pre>
     * </p>
     *
     * @param personId the ID of the person to delete
     * @return HTTP status 204 (No Content)
     */
    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Adds a child to a person.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/child \
     * -H "Content-Type: application/json" \
     * -d '{
     *     "firstName": "Child",
     *     "lastName": "Doe",
     *     "occupation": "Student",
     *     "birthPlace": "New York",
     *     "bornDate": "2000-01-01"
     * }'
     * </pre>
     * </p>
     *
     * @param personId the ID of the parent
     * @param childDTO the child data to create
     * @return the created child with HTTP status 201 (Created)
     */
    @PostMapping("/{personId}/child")
    public ResponseEntity<PersonDTO> addChild(@PathVariable Long personId, @RequestBody PersonDTO childDTO) {
        return new ResponseEntity<>(personService.addChild(personId, childDTO), HttpStatus.CREATED);
    }

    /**
     * Adds an existing child to a person.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/child/2
     * </pre>
     * </p>
     *
     * @param personId the ID of the parent
     * @param childId the ID of the existing child
     * @return the updated child with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/child/{childId}")
    public ResponseEntity<PersonDTO> addChild(@PathVariable Long personId, @PathVariable Long childId) {
        return ResponseEntity.ok(personService.addChild(personId, childId));
    }

    /**
     * Sets a mother for a person.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/mother \
     * -H "Content-Type: application/json" \
     * -d '{
     *     "firstName": "Mother",
     *     "lastName": "Doe",
     *     "occupation": "Teacher",
     *     "birthPlace": "Paris",
     *     "bornDate": "1970-01-01"
     * }'
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @param motherDTO the mother data to create
     * @return the updated person with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/mother")
    public ResponseEntity<PersonDTO> setMother(@PathVariable Long personId, @RequestBody PersonDTO motherDTO) {
        return ResponseEntity.ok(personService.setMother(personId, motherDTO));
    }

    /**
     * Sets an existing person as a mother.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/mother/2
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @param motherId the ID of the existing mother
     * @return the updated person with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/mother/{motherId}")
    public ResponseEntity<PersonDTO> setMother(@PathVariable Long personId, @PathVariable Long motherId) {
        return ResponseEntity.ok(personService.setMother(personId, motherId));
    }

    /**
     * Sets a father for a person.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/father \
     * -H "Content-Type: application/json" \
     * -d '{
     *     "firstName": "Father",
     *     "lastName": "Doe",
     *     "occupation": "Engineer",
     *     "birthPlace": "Berlin",
     *     "bornDate": "1965-01-01"
     * }'
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @param fatherDTO the father data to create
     * @return the updated person with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/father")
    public ResponseEntity<PersonDTO> setFather(@PathVariable Long personId, @RequestBody PersonDTO fatherDTO) {
        return ResponseEntity.ok(personService.setFather(personId, fatherDTO));
    }

    /**
     * Sets an existing person as a father.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/father/2
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @param fatherId the ID of the existing father
     * @return the updated person with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/father/{fatherId}")
    public ResponseEntity<PersonDTO> setFather(@PathVariable Long personId, @PathVariable Long fatherId) {
        return ResponseEntity.ok(personService.setFather(personId, fatherId));
    }

    /**
     * Sets a spouse for a person.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/spouse \
     * -H "Content-Type: application/json" \
     * -d '{
     *     "firstName": "Spouse",
     *     "lastName": "Doe",
     *     "occupation": "Lawyer",
     *     "birthPlace": "Seoul",
     *     "bornDate": "1982-01-01"
     * }'
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @param spouseDTO the spouse data to create
     * @return the updated person with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/spouse")
    public ResponseEntity<PersonDTO> setSpouse(@PathVariable Long personId, @RequestBody PersonDTO spouseDTO) {
        return ResponseEntity.ok(personService.setSpouse(personId, spouseDTO));
    }

    /**
     * Sets an existing person as a spouse.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/spouse/2
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @param spouseId the ID of the existing spouse
     * @return the updated person with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/spouse/{spouseId}")
    public ResponseEntity<PersonDTO> setSpouse(@PathVariable Long personId, @PathVariable Long spouseId) {
        return ResponseEntity.ok(personService.setSpouse(personId, spouseId));
    }

    /**
     * Deletes a spouse relationship.
     * <p>
     * Example:
     * <pre>
     * curl -X DELETE http://localhost:8080/api/person/1/spouse
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @return HTTP status 204 (No Content)
     */
    @DeleteMapping("/{personId}/spouse")
    public ResponseEntity<Void> deleteSpouse(@PathVariable Long personId) {
        personService.deleteSpouse(personId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Adds a former spouse to a person.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/former-spouse \
     * -H "Content-Type: application/json" \
     * -d '{
     *     "firstName": "Former",
     *     "lastName": "Spouse",
     *     "occupation": "Artist",
     *     "birthPlace": "Chicago",
     *     "bornDate": "1985-01-01"
     * }'
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @param formerSpouseDTO the former spouse data to create
     * @return the created former spouse with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/former-spouse")
    public ResponseEntity<PersonDTO> addFormerSpouse(@PathVariable Long personId, @RequestBody PersonDTO formerSpouseDTO) {
        return ResponseEntity.ok(personService.addFormerSpouse(personId, formerSpouseDTO));
    }

    /**
     * Adds an existing person as a former spouse.
     * <p>
     * Example:
     * <pre>
     * curl -X POST http://localhost:8080/api/person/1/former-spouse/2
     * </pre>
     * </p>
     *
     * @param personId the ID of the person
     * @param formerSpouseId the ID of the existing former spouse
     * @return the former spouse with HTTP status 200 (OK)
     */
    @PostMapping("/{personId}/former-spouse/{formerSpouseId}")
    public ResponseEntity<PersonDTO> addFormerSpouse(@PathVariable Long personId, @PathVariable Long formerSpouseId) {
        return ResponseEntity.ok(personService.addFormerSpouse(personId, formerSpouseId));
    }

    /**
     * Finds persons by first name.
     * <p>
     * Example:
     * <pre>
     * curl -X GET http://localhost:8080/api/person/first-name/John
     * </pre>
     * </p>
     *
     * @param firstName the first name to search for
     * @return a list of persons with the specified first name
     */
    @GetMapping("/first-name/{firstName}")
    public ResponseEntity<List<PersonDTO>> findPersonByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(personService.findPersonByFirstName(firstName));
    }

    /**
     * Finds persons by last name.
     * <p>
     * Example:
     * <pre>
     * curl -X GET http://localhost:8080/api/person/last-name/Doe
     * </pre>
     * </p>
     *
     * @param lastName the last name to search for
     * @return a list of persons with the specified last name
     */
    @GetMapping("/last-name/{lastName}")
    public ResponseEntity<List<PersonDTO>> findPersonByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(personService.findPersonByLastName(lastName));
    }
} 