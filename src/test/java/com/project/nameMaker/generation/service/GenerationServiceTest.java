package com.project.nameMaker.generation.service;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.repository.GenerationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenerationServiceTest {

    @Autowired
    private GenerationService generationService;

    @Test
    void generationNames() {
        //given
        GenerationRequestDto generationRequestDto = new GenerationRequestDto();
        generationRequestDto.setLastName("오");

        //when
        List<String> result = generationService.generationNames(generationRequestDto);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(10);
        assertThat(result).anyMatch(name -> name.contains("오"));
    }
}