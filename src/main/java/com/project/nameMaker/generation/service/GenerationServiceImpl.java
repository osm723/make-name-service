package com.project.nameMaker.generation.service;

import com.project.nameMaker.generation.entity.NameGeneration;
import com.project.nameMaker.generation.repository.GenerationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenerationServiceImpl implements GenerationService {

    private final GenerationRepository generationRepository;

    @Override
    public List<String> nameGeneration(String firstName) {
        List<NameGeneration> nameGenerations = generationRepository.nameGeneration();
        List<String> nameResult = new ArrayList<>();

        if (nameGenerations.size() > 2) {
            for (int i = 0; i < 10; i++) {
                Collections.shuffle(nameGenerations);
                NameGeneration firstNameGeneration = nameGenerations.get(i);
                String firstWord = firstNameGeneration.getFirstWord();

                Collections.shuffle(nameGenerations);
                NameGeneration secondNameGeneration = nameGenerations.get(i);
                String secondWord = secondNameGeneration.getSecondWord();

                log.info("pick={}", firstName + firstWord + secondWord);
                nameResult.add(firstName + firstWord + secondWord);
            }
        }

        return nameResult;
    }
}
