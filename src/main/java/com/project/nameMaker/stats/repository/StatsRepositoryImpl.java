package com.project.nameMaker.stats.repository;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.entity.NameStats;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.project.nameMaker.stats.entity.QNameStats.nameStats;


@Repository
@Slf4j
public class StatsRepositoryImpl implements StatsRepositoryQuery {

    private JPAQueryFactory queryFactory;
    public StatsRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<NameStats> findByWhere(Pageable pageable, StatsRequestCond statsRequestCond) {
        log.info("");
        QueryResults<NameStats> results = queryFactory
                .select(nameStats)
                .from(nameStats)
                .where(genderEqual(statsRequestCond.getGender())
                        , nameEqual(statsRequestCond.getName())
                        , startYearGoe(statsRequestCond.getStartYear())
                        , endYearGoe(statsRequestCond.getEndYear())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<NameStats> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

    public BooleanExpression genderEqual(String gender) {
        return StringUtils.hasText(gender) ? nameStats.gender.eq(gender) : null;
    }

    public BooleanExpression nameEqual(String name) {
        return StringUtils.hasText(name) ? nameStats.name.eq(name) : null;
    }

    public BooleanExpression yearsEqual(Integer years) {
        return years != null ? nameStats.years.eq(years) : null;
    }

    private BooleanExpression startYearGoe(Integer startYear) {
        return startYear != null ? nameStats.years.goe(startYear) : null;
    }

    private BooleanExpression endYearGoe(Integer endYear) {
        return endYear != null ? nameStats.years.loe(endYear) : null;
    }

}
