package com.project.nameMaker.generation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Entity
@Getter
public class SecondNameGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @NotEmpty
    private String secondWord;

    @NotEmpty
    private String gender;

    
}
