package com.project.nameMaker.generation.repository;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.dto.GenerationResponseDto;
import com.project.nameMaker.generation.entity.NameGeneration;

import java.util.List;

public interface GenerationRepository {

    List<NameGeneration> nameGeneration();

    List<GenerationResponseDto> generationFirstNames(GenerationRequestDto generationRequestDto);

    List<GenerationResponseDto> generationSecondNames(GenerationRequestDto generationRequestDto);
}
