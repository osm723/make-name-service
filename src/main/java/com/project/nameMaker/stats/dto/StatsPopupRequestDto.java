package com.project.nameMaker.stats.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsPopupRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(max = 4, min = 4)
    private Integer years;
}
