package com.project.nameMaker.generation.service;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
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
    public List<String> nameGeneration(GenerationRequestDto generationRequestDto) {
        List<NameGeneration> nameGenerations = generationRepository.nameGeneration();
        List<String> nameResult = new ArrayList<>();
        int iteratorSize = 10;

        if (nameGenerations.size() > 2) {
            for (int i = 0; i < iteratorSize; i++) {
                String firstWord = duplicationCheck(generationRequestDto.getFirstName(), nameGenerations, generationRequestDto.getLastName());
                String secondWord = duplicationCheck(generationRequestDto.getSecondName(), nameGenerations, firstWord);

                String genName = generationRequestDto.getLastName() + firstWord + secondWord;
                boolean nameDuplication = nameResult.stream().anyMatch(genName::equals);
                log.info("nameGenerations={}, nameDuplication={}", genName, nameDuplication);

                if (nameDuplication) {
                    iteratorSize++;
                } else {
                    nameResult.add(generationRequestDto.getLastName() + firstWord + secondWord);
                }
            }
        }
        return nameResult;
    }

    /**
     * duplicationCheck
     * 첫번째 이름은 성과 같이 안도록 체크
     * 두번째 이름은 첫번째 이름과 같이 안도록 체크
     * @param nameGenerations
     * @param checkName
     * @return
     */
    private String duplicationCheck(String inputName, List<NameGeneration> nameGenerations, String checkName) {
        if ("".equals(inputName)) {
            while (true) {
                Collections.shuffle(nameGenerations);
                NameGeneration firstNameGeneration = nameGenerations.get(0);
                String genWord = firstNameGeneration.getFirstWord();
                if (!genWord.equals(checkName)) {
                    return genWord;
                }
            }
        }

        return inputName;
    }
}
