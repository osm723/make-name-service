package com.project.nameMaker.generation.service;

import com.project.nameMaker.generation.dto.GenerationRequestDto;

import java.util.List;

public interface GenerationService {

    List<String> nameGeneration(GenerationRequestDto generationRequestDto);
}
