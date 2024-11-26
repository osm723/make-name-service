package com.project.nameMaker.generation.repository;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.dto.GenerationResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenerationRepositoryTest {

    @Autowired
    GenerationRepository generationRepository;

    @Test
    void nameGeneration() {
    }

    @Test
    void generationFirstNames() {
        //given
        GenerationRequestDto generationRequestDto = new GenerationRequestDto();

        //when
        List<GenerationResponseDto> result = generationRepository.generationFirstNames(generationRequestDto);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(31);
    }

    @Test
    void generationFirstNames_setGender() {
        //given
        GenerationRequestDto generationRequestDto = new GenerationRequestDto();
        generationRequestDto.setGender("남");

        //when
        List<GenerationResponseDto> result = generationRepository.generationFirstNames(generationRequestDto);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(24);
    }

    @Test
    void generationSecondNames() {
        //given
        GenerationRequestDto generationRequestDto = new GenerationRequestDto();

        //when
        List<GenerationResponseDto> result = generationRepository.generationSecondNames(generationRequestDto);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(31);
    }

    @Test
    void generationSecondNames_setGender() {
        //given
        GenerationRequestDto generationRequestDto = new GenerationRequestDto();
        generationRequestDto.setGender("여");

        //when
        List<GenerationResponseDto> result = generationRepository.generationSecondNames(generationRequestDto);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(21);
    }
}