package com.familytree.familytree.controller;

import com.familytree.familytree.dto.PersonDTO;
import com.familytree.familytree.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.createNewPerson(personDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.updatePerson(personDTO));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long personId) {
        return ResponseEntity.ok(personService.getPerson(personId));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{personId}/child")
    public ResponseEntity<PersonDTO> addChild(@PathVariable Long personId, @RequestBody PersonDTO childDTO) {
        return new ResponseEntity<>(personService.addChild(personId, childDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{personId}/child/{childId}")
    public ResponseEntity<PersonDTO> addChild(@PathVariable Long personId, @PathVariable Long childId) {
        return ResponseEntity.ok(personService.addChild(personId, childId));
    }

    @PostMapping("/{personId}/mother")
    public ResponseEntity<PersonDTO> setMother(@PathVariable Long personId, @RequestBody PersonDTO motherDTO) {
        return ResponseEntity.ok(personService.setMother(personId, motherDTO));
    }

    @PostMapping("/{personId}/mother/{motherId}")
    public ResponseEntity<PersonDTO> setMother(@PathVariable Long personId, @PathVariable Long motherId) {
        return ResponseEntity.ok(personService.setMother(personId, motherId));
    }

    @PostMapping("/{personId}/father")
    public ResponseEntity<PersonDTO> setFather(@PathVariable Long personId, @RequestBody PersonDTO fatherDTO) {
        return ResponseEntity.ok(personService.setFather(personId, fatherDTO));
    }

    @PostMapping("/{personId}/father/{fatherId}")
    public ResponseEntity<PersonDTO> setFather(@PathVariable Long personId, @PathVariable Long fatherId) {
        return ResponseEntity.ok(personService.setFather(personId, fatherId));
    }

    @PostMapping("/{personId}/spouse")
    public ResponseEntity<PersonDTO> setSpouse(@PathVariable Long personId, @RequestBody PersonDTO spouseDTO) {
        return ResponseEntity.ok(personService.setSpouse(personId, spouseDTO));
    }

    @PostMapping("/{personId}/spouse/{spouseId}")
    public ResponseEntity<PersonDTO> setSpouse(@PathVariable Long personId, @PathVariable Long spouseId) {
        return ResponseEntity.ok(personService.setSpouse(personId, spouseId));
    }

    @DeleteMapping("/{personId}/spouse")
    public ResponseEntity<Void> deleteSpouse(@PathVariable Long personId) {
        personService.deleteSpouse(personId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{personId}/former-spouse")
    public ResponseEntity<PersonDTO> addFormerSpouse(@PathVariable Long personId, @RequestBody PersonDTO formerSpouseDTO) {
        return ResponseEntity.ok(personService.addFormerSpouse(personId, formerSpouseDTO));
    }

    @PostMapping("/{personId}/former-spouse/{formerSpouseId}")
    public ResponseEntity<PersonDTO> addFormerSpouse(@PathVariable Long personId, @PathVariable Long formerSpouseId) {
        return ResponseEntity.ok(personService.addFormerSpouse(personId, formerSpouseId));
    }

    @GetMapping("/first-name/{firstName}")
    public ResponseEntity<List<PersonDTO>> findPersonByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(personService.findPersonByFirstName(firstName));
    }

    @GetMapping("/last-name/{lastName}")
    public ResponseEntity<List<PersonDTO>> findPersonByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(personService.findPersonByLastName(lastName));
    }
} 