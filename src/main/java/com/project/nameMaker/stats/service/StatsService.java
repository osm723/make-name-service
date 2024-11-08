package com.project.nameMaker.stats.service;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StatsService {

    Page<StatsResponseDto> findAll(Pageable pageable);

    Page<StatsResponseDto> findByWhere(Pageable pageable, StatsRequestCond statsRequestCond);
}
