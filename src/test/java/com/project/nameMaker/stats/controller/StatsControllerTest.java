package com.project.nameMaker.stats.controller;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import com.project.nameMaker.stats.service.StatsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatsController.class)
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc로 HTTP 요청 테스트

    @MockBean
    private StatsService statsService; // Service 계층은 Mock 처리

    @Test
    @DisplayName("Con | 이름 통계 전체 조회")
    void statsNamesAll() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("name").ascending());
        Page<StatsResponseDto> list = new PageImpl<>(List.of());

        // Mock Service 동작 정의
        when(statsService.findAll(pageRequest)).thenReturn(list);

        // HTTP 요청 테스트
        mockMvc.perform(get("/stats/statsNamesAll")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()); // HTTP 상태 코드 검증
    }

    @Test
    @DisplayName("Con | 이름 통계 초기화면")
    void statsMain() throws Exception {
        // HTTP 요청 테스트
        mockMvc.perform(get("/stats/main")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()); // HTTP 상태 코드 검증
    }

    @Test
    @DisplayName("Con | 이름 통계 조건 조회")
    void statsNames() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("name").ascending());
        Page<StatsResponseDto> list = new PageImpl<>(List.of());
        StatsRequestCond statsRequestCond = new StatsRequestCond();

        // Mock Service 동작 정의
        when(statsService.findByWhere(pageRequest, statsRequestCond)).thenReturn(list);

        // HTTP 요청 테스트
        mockMvc.perform(get("/stats/statsNames")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()); // HTTP 상태 코드 검증
    }

    @Test
    @DisplayName("Con | 이름 통계 조건 조회(페이징)")
    void statsNamesPage() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("name").ascending());
        Page<StatsResponseDto> list = new PageImpl<>(List.of());
        StatsRequestCond statsRequestCond = new StatsRequestCond();

        // Mock Service 동작 정의
        when(statsService.findByWhere(pageRequest, statsRequestCond)).thenReturn(list);

        // HTTP 요청 테스트
        mockMvc.perform(get("/stats/statsNamesPage")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()); // HTTP 상태 코드 검증
    }
}