package com.project.nameMaker.repository;

import com.project.nameMaker.entity.NameStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NameRepository extends JpaRepository<NameStats, Long>, NameRepositoryQuery {

    List<NameStats> findAll();

    //List<NameStats> findByWhere(NameRequestCond nameRequestCond);


}
