package com.project.nameMaker.stats.service;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StatsServiceTest {

    @Autowired
    private StatsService statsService;

    @Test
    @DisplayName("Ser | 이름 통계 전체 조회")
    void findAll() {
        //given
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("name").ascending());

        //when
        Page<StatsResponseDto> result = statsService.findAll(pageRequest);

        //then
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().size()).isEqualTo(20);
    }

    @Test
    @DisplayName("Ser | 이름 통계 조건 조회")
    void findByWhere() {
        //given
        StatsRequestCond statsRequestCond = new StatsRequestCond();
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("name").ascending());
        statsRequestCond.setName("수아");

        //when
        Page<StatsResponseDto> result = statsService.findByWhere(pageRequest, statsRequestCond);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().size()).isEqualTo(12);
        assertThat(result.getContent()).hasSize(12);
        assertThat(result).extracting("name").contains("수아");
    }
}