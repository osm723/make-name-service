package com.project.nameMaker.stats.repository;

import com.project.nameMaker.stats.entity.NameStats;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataJpaTest
class StatsRepositoryTest {

    @Autowired
    private StatsRepository statsRepository;

    @Test
    @DisplayName("Rep | 이름 통계 전체 조회")
    void findAll() {
        //given

        //when
        List<NameStats> result = statsRepository.findAll();

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(680);
        assertThat(result).extracting("name").contains("수아", "서아");
    }
}