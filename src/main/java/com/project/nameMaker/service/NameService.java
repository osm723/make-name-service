package com.project.nameMaker.service;

import com.project.nameMaker.dto.NameRequestCond;
import com.project.nameMaker.dto.NameResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NameService {

    Page<NameResponseDto> findAll(Pageable pageable);

    Page<NameResponseDto> findByWhere(Pageable pageable, NameRequestCond nameRequestCond);
}
