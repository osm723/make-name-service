package com.project.nameMaker.stats.repository;

import com.project.nameMaker.stats.dto.QStatsResponseDto;
import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import com.project.nameMaker.stats.entity.NameStats;
import com.project.nameMaker.stats.entity.QNameStats;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
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
    public Page<StatsResponseDto> findByCond(Pageable pageable, StatsRequestCond statsRequestCond) {
        QNameStats nameStats_1 = QNameStats.nameStats;
        QNameStats nameStats_2 = new QNameStats("nameStatsSub");

        QueryResults<StatsResponseDto> results = queryFactory
                .select(Projections.constructor(StatsResponseDto.class,
                        nameStats_1.name,
                        nameStats_1.gender,
                        nameStats_1.years,
                        nameStats_1.yearRank,
                        nameStats_1.yearCount,
                        JPAExpressions
                                .select(nameStats_2.yearCount.sum())
                                .from(nameStats_2)
                                .where(nameStats_2.name.eq(nameStats_1.name)),
                        JPAExpressions
                                .select(nameStats_2.yearRank.avg().castToNum(Double.class).round())
                                .from(nameStats_2)
                                .where(nameStats_2.name.eq(nameStats_1.name)),
                        JPAExpressions
                                .select(nameStats_2.yearRank.max())
                                .from(nameStats_2)
                                .where(nameStats_2.name.eq(nameStats_1.name)),
                        JPAExpressions
                                .select(nameStats_2.yearRank.min())
                                .from(nameStats_2)
                                .where(nameStats_2.name.eq(nameStats_1.name)),
                        JPAExpressions
                                .select(nameStats_2.yearRank.count())
                                .from(nameStats_2)
                                .where(nameStats_2.name.eq(nameStats_1.name))
                ))
                .from(nameStats_1)
                .where(genderEqual(statsRequestCond.getGender())
                        , nameEqual(statsRequestCond.getName())
                        , startYearGoe(statsRequestCond.getStartYear())
                        , endYearGoe(statsRequestCond.getEndYear())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

            // JPAExpressions alias가 안됨
//        QueryResults<StatsResponseDto> results = queryFactory
//                .select(Projections.constructor(StatsResponseDto.class,
//                        nameStats_1.name,
//                        nameStats_1.gender,
//                        nameStats_1.years,
//                        nameStats_1.yearRank,
//                        nameStats_1.yearCount,
//                        nameStats_2.yearCount.sum().as("totalCount"),
//                        nameStats_2.yearRank.avg().castToNum(Double.class).round().as("totalAvgRank"),
//                        nameStats_2.yearRank.min().as("totalMinRank"),
//                        nameStats_2.yearRank.max().as("totalMaxRank")
//                ))
//                .from(nameStats_1)
//                .innerJoin(
//                        JPAExpressions
//                                .select(
//                                        Projections.constructor(StatsResponseDto.class,
//                                                nameStats_2.name,
//                                                nameStats_2.yearCount.sum().as("totalCount"),
//                                                nameStats_2.yearRank.avg().castToNum(Double.class).round().as("totalAvgRank"),
//                                                nameStats_2.yearRank.min().as("totalMinRank"),
//                                                nameStats_2.yearRank.max().as("totalMaxRank")
//                                        )
//                                )
//                                .from(nameStats_2)
//                                .groupBy(nameStats_2.name)
//                                .as("b")
//                )
//                .where(nameStats_1.name.eq(nameStats_2.name))
//                .offset(pageable.getOffset())  // 페이징 시작 위치
//                .limit(pageable.getPageSize())  // 페이징 크기
//                .fetchResults();

        List<StatsResponseDto> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
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
    private BooleanExpression startYearGoe(Integer startYear) {
        return startYear != null ? nameStats.years.goe(startYear) : null;
    }

    private BooleanExpression endYearGoe(Integer endYear) {
        return endYear != null ? nameStats.years.loe(endYear) : null;
    }

}
