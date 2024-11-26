package com.project.nameMaker.generation.controller;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.service.GenerationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenerationController.class)
class GenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenerationService generationService;

    @Test
    void generationMain() throws Exception {
        // HTTP 요청 테스트
        mockMvc.perform(get("/generation/main")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()); // HTTP 상태 코드 검증
    }

    @Test
    void generationNames() throws Exception {
        GenerationRequestDto generationRequestDto = new GenerationRequestDto();

        // Mock Service 동작 정의
        when(generationService.generationNames(generationRequestDto)).thenReturn(List.of());

        // HTTP 요청 테스트
        mockMvc.perform(get("/generation/generationNames")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()); // HTTP 상태 코드 검증
    }
}