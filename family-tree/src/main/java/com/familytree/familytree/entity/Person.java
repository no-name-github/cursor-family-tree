package com.familytree.familytree.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a person in the family tree.
 * <p>
 * This class maps to the database table 'persons' and contains all the information
 * about a person, including their personal details and relationships to other family members.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
@Entity
@Table(name = "persons")
public class Person {
    
    /**
     * The unique identifier of the person.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the person.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * The middle name of the person (optional).
     */
    @Column
    private String middleName;

    /**
     * The last name of the person.
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * The occupation of the person.
     */
    @Column
    private String occupation;

    /**
     * A detailed life story or biography of the person.
     */
    @Column(columnDefinition = "TEXT")
    private String lifeStory;

    /**
     * The date when the person was born.
     */
    @Column
    private LocalDate bornDate;

    /**
     * The date when the person died (null if still alive).
     */
    @Column
    private LocalDate diedDate;

    /**
     * The place where the person was born.
     */
    @Column
    private String birthPlace;

    /**
     * The current address where the person lives.
     */
    @Column
    private String currentlyLivesAtAddress;

    /**
     * The list of children of this person.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Person> children = new ArrayList<>();

    /**
     * The parent of this person.
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Person parent;

    /**
     * The mother of this person.
     */
    @OneToOne
    @JoinColumn(name = "mother_id")
    private Person mother;

    /**
     * The father of this person.
     */
    @OneToOne
    @JoinColumn(name = "father_id")
    private Person father;

    /**
     * The spouse of this person.
     */
    @OneToOne
    @JoinColumn(name = "spouse_id")
    private Person spouse;

    // Getters and Setters
    /**
     * Gets the ID of the person.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the person.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the middle name of the person.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name of the person.
     *
     * @param middleName the middle name to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the occupation of the person.
     *
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the occupation of the person.
     *
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Gets the life story of the person.
     *
     * @return the life story
     */
    public String getLifeStory() {
        return lifeStory;
    }

    /**
     * Sets the life story of the person.
     *
     * @param lifeStory the life story to set
     */
    public void setLifeStory(String lifeStory) {
        this.lifeStory = lifeStory;
    }

    /**
     * Gets the birth date of the person.
     *
     * @return the birth date
     */
    public LocalDate getBornDate() {
        return bornDate;
    }

    /**
     * Sets the birth date of the person.
     *
     * @param bornDate the birth date to set
     */
    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    /**
     * Gets the death date of the person.
     *
     * @return the death date
     */
    public LocalDate getDiedDate() {
        return diedDate;
    }

    /**
     * Sets the death date of the person.
     *
     * @param diedDate the death date to set
     */
    public void setDiedDate(LocalDate diedDate) {
        this.diedDate = diedDate;
    }

    /**
     * Gets the birth place of the person.
     *
     * @return the birth place
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * Sets the birth place of the person.
     *
     * @param birthPlace the birth place to set
     */
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * Gets the current address of the person.
     *
     * @return the current address
     */
    public String getCurrentlyLivesAtAddress() {
        return currentlyLivesAtAddress;
    }

    /**
     * Sets the current address of the person.
     *
     * @param currentlyLivesAtAddress the current address to set
     */
    public void setCurrentlyLivesAtAddress(String currentlyLivesAtAddress) {
        this.currentlyLivesAtAddress = currentlyLivesAtAddress;
    }

    /**
     * Gets the list of children of the person.
     *
     * @return the list of children
     */
    public List<Person> getChildren() {
        return children;
    }

    /**
     * Sets the list of children of the person.
     *
     * @param children the list of children to set
     */
    public void setChildren(List<Person> children) {
        this.children = children;
    }

    /**
     * Gets the parent of the person.
     *
     * @return the parent
     */
    public Person getParent() {
        return parent;
    }

    /**
     * Sets the parent of the person.
     *
     * @param parent the parent to set
     */
    public void setParent(Person parent) {
        this.parent = parent;
    }

    /**
     * Gets the mother of the person.
     *
     * @return the mother
     */
    public Person getMother() {
        return mother;
    }

    /**
     * Sets the mother of the person.
     *
     * @param mother the mother to set
     */
    public void setMother(Person mother) {
        this.mother = mother;
    }

    /**
     * Gets the father of the person.
     *
     * @return the father
     */
    public Person getFather() {
        return father;
    }

    /**
     * Sets the father of the person.
     *
     * @param father the father to set
     */
    public void setFather(Person father) {
        this.father = father;
    }

    /**
     * Gets the spouse of the person.
     *
     * @return the spouse
     */
    public Person getSpouse() {
        return spouse;
    }

    /**
     * Sets the spouse of the person.
     *
     * @param spouse the spouse to set
     */
    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }
} 