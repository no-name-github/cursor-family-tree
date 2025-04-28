package com.familytree.familytree.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class PersonDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String occupation;
    private String lifeStory;
    private LocalDate bornDate;
    private LocalDate diedDate;
    private String birthPlace;
    private String currentlyLivesAtAddress;
    
    // Relationships
    private Long motherId;
    private Long fatherId;
    private Long spouseId;
} 