package com.familytree.familytree.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String occupation;

    @Column(columnDefinition = "TEXT")
    private String lifeStory;

    @Column
    private LocalDate bornDate;

    @Column
    private LocalDate diedDate;

    @Column
    private String birthPlace;

    @Column
    private String currentlyLivesAtAddress;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Person> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Person parent;

    @OneToOne
    @JoinColumn(name = "mother_id")
    private Person mother;

    @OneToOne
    @JoinColumn(name = "father_id")
    private Person father;

    @OneToOne
    @JoinColumn(name = "spouse_id")
    private Person spouse;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLifeStory() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory = lifeStory;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public LocalDate getDiedDate() {
        return diedDate;
    }

    public void setDiedDate(LocalDate diedDate) {
        this.diedDate = diedDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getCurrentlyLivesAtAddress() {
        return currentlyLivesAtAddress;
    }

    public void setCurrentlyLivesAtAddress(String currentlyLivesAtAddress) {
        this.currentlyLivesAtAddress = currentlyLivesAtAddress;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public Person getParent() {
        return parent;
    }

    public void setParent(Person parent) {
        this.parent = parent;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }
} 