package com.project.nameMaker.stats.controller;

import com.project.nameMaker.stats.dto.*;
import com.project.nameMaker.stats.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/stats")
@Slf4j
public class StatsController {

    private final StatsService statsService;

    // 전체 조회
//    @GetMapping("/namesAll")
//    public List<NameResponseDto> namesAll() {
//        return nameService.findAll();
//    }

//    @GetMapping("/namesAll")
//    public Map<String, Object> namesAll() {
//        List<NameResponseDto> nameList = nameService.findAll();
//        // Map에 리스트를 담아서 반환
//        Map<String, Object> response = new HashMap<>();
//        response.put("nameList", nameList);
//
//        return response;
//    }

//    @GetMapping("/namesAll")
//    public Page<NameResponseDto> namesAll(Pageable pageable) {
//        return nameService.findAll(pageable);
//    }

    /**
     * namesAll
     * 이름 통계 전체 조회
     * @param statsRequestCond
     * @param model
     * @param pageable
     * @return viewPath
     */
    @GetMapping("/statsNamesAll")
    public String statsNamesAll(@ModelAttribute("statsName") StatsRequestCond statsRequestCond, Model model, Pageable pageable) {
        Page<StatsResponseDto> names = statsService.findAll(pageable);
        model.addAttribute("names", names);
        return "/name/stats/statsMain";
    }

    /**
     * statsMain
     * 이름 통계 초기화면
     * @param statsRequestCond
     * @return viewPath
     */
    @GetMapping("/main")
    public String statsMain(@ModelAttribute("statsName") StatsRequestCond statsRequestCond) {
        return "/name/stats/statsMain";
    }

    /**
     * statsNames
     * 이름 통계 조건부 조회
     * @param statsRequestCond
     * @param model
     * @param pageable
     * @return viewPath
     */
    @GetMapping("/statsNames")
    public String statsNames(@ModelAttribute("statsName") StatsRequestCond statsRequestCond, Model model, Pageable pageable) {
        Page<StatsResponseDto> names = statsService.findByWhere(pageable, statsRequestCond);
        model.addAttribute("names", names);
        return "/name/stats/statsMain";
    }

    /**
     * statsNamesPage
     * 이름 통계 조건부 조회 (화면 페이징)
     * @param statsRequestCond
     * @param model
     * @param pageable
     * @return status
     */
    @GetMapping("/statsNamesPage")
    public ResponseEntity<Page<StatsResponseDto>> statsNamesPage(@ModelAttribute("statsName") StatsRequestCond statsRequestCond, Model model, Pageable pageable) {
        Page<StatsResponseDto> names = statsService.findByWhere(pageable, statsRequestCond);
        model.addAttribute("names", names);
        return ResponseEntity.ok(names);
    }

    /**
     * detailPopup
     * 이름 상세 페이지
     * @param statsPopupRequestDto
     * @param model
     * @return viewPath
     */
    @GetMapping("/detailPopup")
    public String detailPopup(@ModelAttribute StatsPopupRequestDto statsPopupRequestDto, Model model) {
        StatsPopupResponseDto popupName = statsService.findByNameAndYears(statsPopupRequestDto);
        model.addAttribute("popupName", popupName);
        return "/name/detail/popup";
    }

}

