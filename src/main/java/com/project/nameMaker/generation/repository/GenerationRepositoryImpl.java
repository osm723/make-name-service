package com.project.nameMaker.generation.repository;

import com.project.nameMaker.generation.entity.NameGeneration;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.nameMaker.generation.entity.QNameGeneration.nameGeneration;

@Repository
public class GenerationRepositoryImpl implements GenerationRepository {

    private JPAQueryFactory queryFactory;

    public GenerationRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<NameGeneration> nameGeneration() {

        List<NameGeneration> result = queryFactory
                .select(nameGeneration)
                .from(nameGeneration)
                .fetch();

        return result;
    }
}
