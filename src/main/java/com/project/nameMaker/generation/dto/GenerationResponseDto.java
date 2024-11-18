package com.project.nameMaker.generation.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerationResponseDto {

    private String genName;

    @QueryProjection
    public GenerationResponseDto(String genName) {
        this.genName = genName;
    }

}
