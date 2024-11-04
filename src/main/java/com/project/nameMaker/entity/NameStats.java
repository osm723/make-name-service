package com.project.nameMaker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class NameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(max = 2, min = 2)
    private String gender;

    @NotEmpty
    @Size(max = 4, min = 4)
    private int years;

    @NotEmpty
    @Size(max = 2)
    private int yearRank;

    private int totalRank;

    @NotEmpty
    private int YEAR_COUNT;

    /*
    이름
    성별
    연도
    연도 순위
    전체 순위
    연도 비율
     */

}
