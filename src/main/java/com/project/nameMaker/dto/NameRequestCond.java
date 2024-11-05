package com.project.nameMaker.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameRequestCond {

    private String name;

    @Size(max = 2, min = 2)
    private String gender;

    @Size(max = 4, min = 4)
    private Integer years;

    @Size(max = 2)
    private Integer yearRank;

    private Integer totalRank;

    private Integer yearCount;
}
