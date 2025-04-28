package com.familytree.familytree.repository;

import com.familytree.familytree.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

/**
 * Repository interface for Person entity.
 * <p>
 * This interface extends JpaRepository to provide basic CRUD operations and
 * adds custom query methods for finding persons by various criteria.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    /**
     * Finds a person by their first name and last name.
     *
     * @param firstName the first name to search for
     * @param lastName the last name to search for
     * @return an Optional containing the person if found, empty otherwise
     */
    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);
    
    /**
     * Finds all persons with the specified first name.
     *
     * @param firstName the first name to search for
     * @return a list of persons with the specified first name
     */
    List<Person> findByFirstName(String firstName);
    
    /**
     * Finds all persons with the specified last name.
     *
     * @param lastName the last name to search for
     * @return a list of persons with the specified last name
     */
    List<Person> findByLastName(String lastName);
    
    /**
     * Finds all persons born in the specified place.
     *
     * @param birthPlace the birth place to search for
     * @return a list of persons born in the specified place
     */
    List<Person> findByBirthPlace(String birthPlace);
    
    /**
     * Finds all persons with the specified occupation.
     *
     * @param occupation the occupation to search for
     * @return a list of persons with the specified occupation
     */
    List<Person> findByOccupation(String occupation);
    
    /**
     * Finds all persons born between the specified dates.
     *
     * @param startDate the start date (inclusive)
     * @param endDate the end date (inclusive)
     * @return a list of persons born between the specified dates
     */
    List<Person> findByBornDateBetween(LocalDate startDate, LocalDate endDate);
    
    /**
     * Finds all persons who are currently alive (diedDate is null).
     *
     * @return a list of persons who are currently alive
     */
    List<Person> findByDiedDateIsNull();
    
    /**
     * Finds all persons who have died (diedDate is not null).
     *
     * @return a list of persons who have died
     */
    List<Person> findByDiedDateIsNotNull();
    
    /**
     * Finds all persons who have children.
     *
     * @return a list of persons who have children
     */
    List<Person> findByChildrenIsNotEmpty();
    
    /**
     * Finds all persons who are married (have a spouse).
     *
     * @return a list of persons who are married
     */
    List<Person> findBySpouseIsNotNull();
} 