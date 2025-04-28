package com.familytree.familytree.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

/**
 * Data Transfer Object for Person entity.
 * <p>
 * This class represents a person in the family tree with their personal information
 * and relationships to other family members.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
@Data
@Builder
public class PersonDTO {
    /**
     * The unique identifier of the person.
     */
    private Long id;

    /**
     * The first name of the person.
     */
    private String firstName;

    /**
     * The middle name of the person (optional).
     */
    private String middleName;

    /**
     * The last name of the person.
     */
    private String lastName;

    /**
     * The occupation of the person.
     */
    private String occupation;

    /**
     * A detailed life story or biography of the person.
     */
    private String lifeStory;

    /**
     * The date when the person was born.
     */
    private LocalDate bornDate;

    /**
     * The date when the person died (null if still alive).
     */
    private LocalDate diedDate;

    /**
     * The place where the person was born.
     */
    private String birthPlace;

    /**
     * The current address where the person lives.
     */
    private String currentlyLivesAtAddress;
    
    /**
     * The ID of the person's mother.
     */
    private Long motherId;

    /**
     * The ID of the person's father.
     */
    private Long fatherId;

    /**
     * The ID of the person's spouse.
     */
    private Long spouseId;
} 