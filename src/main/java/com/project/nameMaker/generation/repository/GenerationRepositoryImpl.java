package com.project.nameMaker.generation.repository;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.dto.GenerationResponseDto;
import com.project.nameMaker.generation.dto.QGenerationResponseDto;
import com.project.nameMaker.generation.entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.project.nameMaker.generation.entity.QFirstNameGeneration.*;
import static com.project.nameMaker.generation.entity.QNameGeneration.nameGeneration;
import static com.project.nameMaker.generation.entity.QSecondNameGeneration.*;

@Repository
@Slf4j
public class GenerationRepositoryImpl implements GenerationRepository {

    private JPAQueryFactory queryFactory;

    private static final String COMMON_GENDER = "공통";

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

    @Override
    public List<GenerationResponseDto> generationFirstNames(GenerationRequestDto generationRequestDto) {
        log.info("gender={}",generationRequestDto.getGender());
        List<GenerationResponseDto> result = queryFactory
                .select(new QGenerationResponseDto(firstNameGeneration.firstWord))
                .from(firstNameGeneration)
                .where(firstNameGenderEq(COMMON_GENDER)
                        .or(firstNameGenderEq(generationRequestDto.getGender()))
                )
                .fetch();

        return result;
    }

    @Override
    public List<GenerationResponseDto> generationSecondNames(GenerationRequestDto generationRequestDto) {
        List<GenerationResponseDto> result = queryFactory
                .select(new QGenerationResponseDto(secondNameGeneration.secondWord))
                .from(secondNameGeneration)
                .where(secondNameGenderEq(COMMON_GENDER)
                        .or(secondNameGenderEq(generationRequestDto.getGender()))
                )
                .fetch();

        return result;
    }

    private BooleanExpression firstNameGenderEq(String gender) {
        return StringUtils.hasText(gender) ? firstNameGeneration.gender.eq(gender) : null;
    }

    private BooleanExpression secondNameGenderEq(String gender) {
        return StringUtils.hasText(gender) ? secondNameGeneration.gender.eq(gender) : null;
    }


}
