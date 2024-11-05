package com.project.nameMaker.repository;

import com.project.nameMaker.dto.NameRequestCond;
import com.project.nameMaker.entity.NameStats;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.nameMaker.entity.QNameStats.*;

@Repository
public class NameRepositoryImpl implements NameRepositoryQuery {

    private JPAQueryFactory queryFactory;

    private NameWhereCond whereCond;

    public NameRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<NameStats> findByWhere(Pageable pageable, NameRequestCond nameRequestCond) {
        QueryResults<NameStats> results = queryFactory
                .select(nameStats)
                .from(nameStats)
                .where(whereCond.genderEqual(nameRequestCond.getGender())
                        , whereCond.nameEqual(nameRequestCond.getName())
                        , whereCond.yearsEqual(nameRequestCond.getYears())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<NameStats> contents = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(contents, pageable, total);
    }

}
