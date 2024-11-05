package com.project.nameMaker.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static com.project.nameMaker.entity.QNameStats.nameStats;

public class NameWhereCond {

    public BooleanExpression genderEqual(String gender) {
        return StringUtils.hasText(gender) ? nameStats.gender.eq(gender) : null;
    }

    public BooleanExpression nameEqual(String name) {
        return StringUtils.hasText(name) ? nameStats.name.eq(name) : null;
    }

    public BooleanExpression yearsEqual(Integer years) {
        return years != null ? nameStats.years.eq(years) : null;
    }
}
