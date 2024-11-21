package com.project.nameMaker.stats.repository;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import com.project.nameMaker.stats.entity.NameStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StatsRepositoryQuery {

    Page<NameStats> findByWhere(Pageable pageable, StatsRequestCond statsRequestCond);

    Page<StatsResponseDto> findByCond(Pageable pageable, StatsRequestCond statsRequestCond);
}
