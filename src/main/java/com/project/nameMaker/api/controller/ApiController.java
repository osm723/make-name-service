package com.project.nameMaker.api.controller;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.service.GenerationService;
import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import com.project.nameMaker.stats.service.StatsService;
import com.project.nameMaker.utils.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiController {

    private final StatsService statsService;

    private final CookieUtils cookieUtils;

    private final GenerationService generationService;


    /**
     * statsNamesApi
     * 이름 통계 조회 Api
     * @param pageable
     * @param statsRequestCond
     * @return names
     */
    @RequestMapping("/stats/names")
    //@PostMapping("/statsNames")
    public Page<StatsResponseDto> statsNamesApi(Pageable pageable, StatsRequestCond statsRequestCond) {
        Page<StatsResponseDto> names = statsService.findByWhere(pageable, statsRequestCond);
        return names;
    }

    /**
     * saveNamesApi
     * 이름 저장 조회 Api
     * 개인 쿠키값을 가져오는 구조라 사실 API는 무의미 하지만 만듬
     * @param request
     * @return names
     */
    @RequestMapping("/save/names")
    //@PostMapping("/save/names")
    public Map<String, Object> saveNamesApi(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", cookieUtils.getCookie(request));
        return response;
    }

    /**
     * generationNames
     * 이름 생성 조회 Api
     * @param generationRequestDto
     * @return names
     */
    @RequestMapping("/generation/names")
    //@PostMapping("/generation/names")
    public Map<String, Object> generationNames(GenerationRequestDto generationRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", generationService.generationNames(generationRequestDto));
        return response;
    }

}
