package com.project.nameMaker.generation.service;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.dto.GenerationResponseDto;
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

    private static final int GENERATION_NAME_SIZE = 10;

    @Override
    public List<String> generationNames(GenerationRequestDto generationRequestDto) {
        List<GenerationResponseDto> generationFirstNames = generationRepository.generationFirstNames(generationRequestDto);
        List<GenerationResponseDto> generationSecondNames = generationRepository.generationSecondNames(generationRequestDto);
        List<String> generationNames = new ArrayList<>();
        int iteratorSize = GENERATION_NAME_SIZE;
        String genFirstName = generationRequestDto.getFirstName();
        String genSecondName = generationRequestDto.getSecondName();

        if (generationFirstNames.size() > 3 && generationSecondNames.size() > 3) {
            for (int i = 0; i < iteratorSize; i++) {
                if ("".equals(generationRequestDto.getFirstName())) {
                    genFirstName = duplicationCheck(generationFirstNames, generationRequestDto.getLastName());
                }

                if ("".equals(generationRequestDto.getSecondName())) {
                    genSecondName = duplicationCheck(generationSecondNames, genFirstName);
                }

                // 성 + 첫번째 이름 + 두번째 이름 조합
                String generationName = generationRequestDto.getLastName() + genFirstName + genSecondName;

                // 기존 생성된 이름 list에 중복 체크
                boolean duplicationName = generationNames.stream().anyMatch(generationName::equals);
                log.info("nameGenerations={}, duplicationName={}", generationName, duplicationName);

                if (duplicationName) {
                    iteratorSize++;
                } else {
                    generationNames.add(generationRequestDto.getLastName() + genFirstName + genSecondName);
                }
            }
        }
        return generationNames;
    }

    /**
     * duplicationCheck
     * 첫번째 이름은 성과 같이 안도록 체크
     * 두번째 이름은 첫번째 이름과 같이 안도록 체크
     * @param generationNames
     * @param checkName
     * @return
     */
    private String duplicationCheck(List<GenerationResponseDto> generationNames, String checkName) {
        while (true) {
            Collections.shuffle(generationNames);
            GenerationResponseDto pickUpName = generationNames.get(0);
            String genName = pickUpName.getGenName();
            log.info("genName={}", genName);
            log.info("checkName={}", checkName);
            if (!genName.equals(checkName)) {
                return genName;
            }
        }
    }


//    @Override
//    public List<String> nameGeneration(GenerationRequestDto generationRequestDto) {
//        List<NameGeneration> nameGenerations = generationRepository.nameGeneration();
//        List<String> nameResult = new ArrayList<>();
//        int iteratorSize = 10;
//
//        if (nameGenerations.size() > 2) {
//            for (int i = 0; i < iteratorSize; i++) {
//                String firstWord = duplicationCheck(generationRequestDto.getFirstName(), nameGenerations, generationRequestDto.getLastName());
//                String secondWord = duplicationCheck(generationRequestDto.getSecondName(), nameGenerations, firstWord);
//
//                String genName = generationRequestDto.getLastName() + firstWord + secondWord;
//                boolean nameDuplication = nameResult.stream().anyMatch(genName::equals);
//                log.info("nameGenerations={}, nameDuplication={}", genName, nameDuplication);
//
//                if (nameDuplication) {
//                    iteratorSize++;
//                } else {
//                    nameResult.add(generationRequestDto.getLastName() + firstWord + secondWord);
//                }
//            }
//        }
//        return nameResult;
//    }

    /**
     * duplicationCheck
     * 첫번째 이름은 성과 같이 안도록 체크
     * 두번째 이름은 첫번째 이름과 같이 안도록 체크
     * @param nameGenerations
     * @param checkName
     * @return
     */
//    private String duplicationCheck(String inputName, List<NameGeneration> nameGenerations, String checkName) {
//        if ("".equals(inputName)) {
//            while (true) {
//                Collections.shuffle(nameGenerations);
//                NameGeneration firstNameGeneration = nameGenerations.get(0);
//                String genWord = firstNameGeneration.getFirstWord();
//                if (!genWord.equals(checkName)) {
//                    return genWord;
//                }
//            }
//        }
//
//        return inputName;
//    }
}
