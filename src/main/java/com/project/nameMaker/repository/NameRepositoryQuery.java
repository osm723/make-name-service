package com.project.nameMaker.repository;

import com.project.nameMaker.dto.NameRequestCond;
import com.project.nameMaker.entity.NameStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NameRepositoryQuery {

    Page<NameStats> findByWhere(Pageable pageable, NameRequestCond nameRequestCond);
}
