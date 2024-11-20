package com.project.nameMaker.stats.controller;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
     * 이름 통계
     * 전체 조회
     * @param pageable
     * @return Page<NameResponseDto>
     */
    @GetMapping("/statsNamesAll")
    public String namesAll(Model model, Pageable pageable) {
        Page<StatsResponseDto> names = statsService.findAll(pageable);
        setModel(model, new StatsRequestCond());
        return "/name/stats/statsMain";
    }

    /**
     * 이름 통계
     * 메인 조회
     * @param model
     * @return Page<NameResponseDto>
     */
    @GetMapping("/main")
    public String statsMain(Model model) {
        setModel(model, new StatsRequestCond());
        return "/name/stats/statsMain";

    }

    /**
     * 이름 통계
     * 조건부 조회
     * @param model
     * @param pageable
     * @param statsRequestCond
     * @return Page<NameResponseDto>
     */
    @GetMapping("/statsNames")
    public String namesSearch(Model model, Pageable pageable, StatsRequestCond statsRequestCond) {
        Page<StatsResponseDto> names = statsService.findByWhere(pageable, statsRequestCond);
        setModel(model, statsRequestCond);
        model.addAttribute("names", names);

        return "/name/stats/statsMain";
    }

    @GetMapping("/statsNamesPage")
    public ResponseEntity<Page<StatsResponseDto>> getStatsNamesPage(Model model, Pageable pageable, StatsRequestCond statsRequestCond) {
        Page<StatsResponseDto> names = statsService.findByWhere(pageable, statsRequestCond);
        setModel(model, statsRequestCond);

        return ResponseEntity.ok(names);
    }

    @ModelAttribute("years")
    public List<Integer> initYears() {
        List<Integer> years = IntStream.rangeClosed(2008, LocalDate.now().getYear())
                .boxed()
                .collect(Collectors.toList());
        return years;
    }

    private static void setModel(Model model, StatsRequestCond statsRequestCond) {
        model.addAttribute("startYear", statsRequestCond.getStartYear() != null ? statsRequestCond.getStartYear() : 2024 );
        model.addAttribute("endYear", statsRequestCond.getEndYear() != null ? statsRequestCond.getEndYear() : 2024 );
        model.addAttribute("selectedGender", statsRequestCond.getGender() != null ? statsRequestCond.getGender() : "");
        model.addAttribute("name", statsRequestCond.getName());
    }

}
