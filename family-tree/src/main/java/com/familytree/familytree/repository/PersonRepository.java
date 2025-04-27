package com.familytree.familytree.repository;

import com.familytree.familytree.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    // Find by first name and last name
    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);
    
    // Find all persons by last name
    List<Person> findByLastName(String lastName);
    
    // Find all persons by birth place
    List<Person> findByBirthPlace(String birthPlace);
    
    // Find all persons by occupation
    List<Person> findByOccupation(String occupation);
    
    // Find all persons born between two dates
    List<Person> findByBornDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Find all persons who are currently alive (diedDate is null)
    List<Person> findByDiedDateIsNull();
    
    // Find all persons who have died (diedDate is not null)
    List<Person> findByDiedDateIsNotNull();
    
    // Find all persons who have children
    List<Person> findByChildrenIsNotEmpty();
    
    // Find all persons who are married (have a spouse)
    List<Person> findBySpouseIsNotNull();
} 