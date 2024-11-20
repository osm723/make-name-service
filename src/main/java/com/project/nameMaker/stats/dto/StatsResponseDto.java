package com.project.nameMaker.stats.dto;

import com.project.nameMaker.stats.entity.NameStats;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class StatsResponseDto {

    public StatsResponseDto(String name, String gender, Integer years, Integer yearRank, Integer totalRank, Integer yearCount, int pageSize) {
        this.name = name;
        this.gender = gender;
        this.years = years;
        this.yearRank = yearRank;
        this.totalRank = totalRank;
        this.yearCount = yearCount;
        this.pageSize = 10;
    }

    public StatsResponseDto(NameStats nameStats) {
        this.name = nameStats.getName();
        this.gender = nameStats.getGender();
        this.years = nameStats.getYears();
        this.yearRank = nameStats.getYearRank();
        this.totalRank = nameStats.getTotalRank();
        this.yearCount = nameStats.getYearCount();
    }

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(max = 2, min = 2)
    private String gender;

    @NotEmpty
    @Size(max = 4, min = 4)
    private Integer years;

    @NotEmpty
    @Size(max = 2)
    private Integer yearRank;

    private Integer totalRank;

    @NotEmpty
    private Integer yearCount;

    private int pageSize;

}
