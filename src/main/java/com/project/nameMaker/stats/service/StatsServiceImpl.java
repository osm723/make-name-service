package com.project.nameMaker.stats.service;

import com.project.nameMaker.stats.dto.*;
import com.project.nameMaker.stats.entity.NameStats;
import com.project.nameMaker.stats.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;

    private final ModelMapper modelMapper;

    private static final String TRANSLATE_FILE_NAME = "src/main/resources/translate/hanja.txt";

    @Override
    public Page<StatsResponseDto> findAll(Pageable pageable) {
        Page<NameStats> nameStatsList = statsRepository.findAll(pageable);
        return nameStatsList.map(StatsResponseDto::new);
    }

    @Override
    public Page<StatsResponseDto> findByWhere(Pageable pageable, StatsRequestCond statsRequestCond) {
        //Page<NameStats> nameStatsWhereList = statsRepository.findByWhere(pageable, statsRequestCond);
        //return nameStatsWhereList.map(StatsResponseDto::new);
        return statsRepository.findByCond(pageable, statsRequestCond);
    }

    @Override
    public StatsPopupResponseDto findByNameAndYears(StatsPopupRequestDto statsPopupRequestDto) {
        NameStats result = statsRepository.findByNameAndYears(statsPopupRequestDto.getName(), statsPopupRequestDto.getYears());
        StatsPopupResponseDto statsPopupResponseDto = modelMapper.map(result, StatsPopupResponseDto.class);

        // 한자 찾기
        String name = statsPopupRequestDto.getName();
        statsPopupResponseDto.setFirstNameTranslate(translate(name.substring(0, 1)));
        statsPopupResponseDto.setSecondNameTranslate(translate(name.substring(1, 2)));

        return statsPopupResponseDto;
    }

    private List<String> translate(String name) {
        List<String> nameTranslate = extractGaBlock(name);

        if (nameTranslate.isEmpty()) {
            log.info("한자 번역 실패");
        }

        return nameTranslate;
    }

    public static List<String> extractGaBlock(String name) {
        List<String> gaBlock = new ArrayList<>();
        boolean isBlock = false;
        String setName = "[" + name + "]";

        try (BufferedReader br = new BufferedReader(new FileReader(TRANSLATE_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals(setName)) {
                    isBlock = true;
                    continue;
                }
                if (isBlock && line.trim().startsWith("[")) {
                    break;
                }

                if (isBlock) {
                    String[] parts = line.split("=");

                    if (parts.length > 1) {
                        // '=' 뒤의 부분에서 첫 번째 ',' 기준으로만 가져오기
                        String meaning = parts[1].split(",")[0].trim();

                        // '=' 앞의 부분과 결합하여 저장
                        gaBlock.add(parts[0] + "=" + meaning);
                    }
                    //gaBlock.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gaBlock;
    }
}
