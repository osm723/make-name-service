package com.project.nameMaker.stats.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsRequestCond {

    private String name;

    @Size(max = 2, min = 2)
    private String gender;

    @Size(max = 4, min = 4)
    private Integer startYear;

    @Size(max = 4, min = 4)
    private Integer endYear;

    @Size(max = 2)
    private Integer yearRank;

    private Integer totalRank;

    private Integer yearCount;
}
