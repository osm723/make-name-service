package com.project.nameMaker.service;

import com.project.nameMaker.dto.NameRequestCond;
import com.project.nameMaker.dto.NameResponseDto;
import com.project.nameMaker.entity.NameStats;
import com.project.nameMaker.repository.NameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NameServiceImpl implements NameService {

    private final NameRepository nameRepository;

    @Override
    public Page<NameResponseDto> findAll(Pageable pageable) {
        Page<NameStats> nameStatsList = nameRepository.findAll(pageable);
        return nameStatsList.map(NameResponseDto::new);
    }

    @Override
    public Page<NameResponseDto> findByWhere(Pageable pageable, NameRequestCond nameRequestCond) {
        Page<NameStats> nameStatsWhereList = nameRepository.findByWhere(pageable, nameRequestCond);
        return nameStatsWhereList.map(NameResponseDto::new);
    }
}
