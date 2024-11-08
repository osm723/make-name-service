package com.project.nameMaker.stats.repository;

import com.project.nameMaker.stats.entity.NameStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatsRepository extends JpaRepository<NameStats, Long>, StatsRepositoryQuery {

    List<NameStats> findAll();

    //List<NameStats> findByWhere(NameRequestCond nameRequestCond);


}
