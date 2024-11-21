package com.project.nameMaker.stats.dto;

import com.project.nameMaker.stats.entity.NameStats;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class StatsResponseDto {

    public StatsResponseDto(NameStats nameStats) {
        this.name = nameStats.getName();
        this.gender = nameStats.getGender();
        this.years = nameStats.getYears();
        this.yearRank = nameStats.getYearRank();
        this.yearCount = nameStats.getYearCount();
    }

    @QueryProjection
    public StatsResponseDto(String name, String gender, Integer years, Integer yearRank, Long yearCount, Long totalCount, Double totalAvgRank, Integer totalMaxRank, Integer totalMinRank, Long totalRankCount) {
        this.name = name;
        this.gender = gender;
        this.years = years;
        this.yearRank = yearRank;
        this.yearCount = yearCount;
        this.totalCount = totalCount;
        this.totalAvgRank = totalAvgRank;
        this.totalMaxRank = totalMaxRank;
        this.totalMinRank = totalMinRank;
        this.totalRankCount = totalRankCount;
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

    @NotEmpty
    private Long yearCount;

    private Long totalCount;      // sum 결과는 Long

    private Double totalAvgRank;  // avg 결과는 Double

    private Integer totalMaxRank; // min, max 결과는 Integer

    private Integer totalMinRank;

    private Long totalRankCount;



}
