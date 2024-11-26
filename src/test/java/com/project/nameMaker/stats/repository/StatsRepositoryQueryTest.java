package com.project.nameMaker.stats.repository;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import com.project.nameMaker.stats.entity.NameStats;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StatsRepositoryQueryTest {

    @Autowired
    private StatsRepository statsRepository;

    @Test
    @DisplayName("Rep | 이름 통계 조건 조회")
    void findByWhere() {
        //given
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("name").ascending());
        Page<StatsResponseDto> list = new PageImpl<>(List.of());
        StatsRequestCond statsRequestCond = new StatsRequestCond();
        statsRequestCond.setName("수아");

        //when
        Page<NameStats> result = statsRepository.findByWhere(pageRequest, statsRequestCond);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).hasSize(12);
        assertThat(result).extracting("name").contains("수아");
    }

    @Test
    @DisplayName("Rep | 이름 통계 조건 조회(통계)")
    void findByCond() {
        //given
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("name").ascending());
        Page<StatsResponseDto> list = new PageImpl<>(List.of());
        StatsRequestCond statsRequestCond = new StatsRequestCond();
        statsRequestCond.setName("서아");

        //when
        Page<StatsResponseDto> result = statsRepository.findByCond(pageRequest, statsRequestCond);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).hasSize(9);
        assertThat(result).extracting("name").contains("서아");
        assertThat(result).extracting("gender").contains("여");
        assertThat(result).extracting("totalCount").contains(17864L);
        assertThat(result).extracting("totalAvgRank").contains(5.0);
        assertThat(result).extracting("totalMaxRank").contains(18);
        assertThat(result).extracting("totalMinRank").contains(1);
        assertThat(result).extracting("totalRankCount").contains(9L);
    }
}