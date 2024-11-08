package com.project.nameMaker.stats.service;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import com.project.nameMaker.stats.entity.NameStats;
import com.project.nameMaker.stats.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    @Override
    public Page<StatsResponseDto> findAll(Pageable pageable) {
        Page<NameStats> nameStatsList = statsRepository.findAll(pageable);
        return nameStatsList.map(StatsResponseDto::new);
    }

    @Override
    public Page<StatsResponseDto> findByWhere(Pageable pageable, StatsRequestCond statsRequestCond) {
        Page<NameStats> nameStatsWhereList = statsRepository.findByWhere(pageable, statsRequestCond);
        return nameStatsWhereList.map(StatsResponseDto::new);
    }
}
