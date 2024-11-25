package com.project.nameMaker.stats.controller;

import com.project.nameMaker.stats.dto.StatsRequestCond;
import com.project.nameMaker.stats.dto.StatsResponseDto;
import com.project.nameMaker.stats.service.StatsService;
import lombok.RequiredArgsConstructor;
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
     * @param model
     * @param pageable
     * @return viewPath
     */
    @GetMapping("/statsNamesAll")
    public String statsNamesAll(Model model, Pageable pageable) {
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
     * initYears
     * 연도 설정 값
     * @return years
     */
    @ModelAttribute("years")
    public List<Integer> initYears() {
        List<Integer> years = IntStream.rangeClosed(2008, LocalDate.now().getYear())
                .boxed()
                .collect(Collectors.toList());
        return years;
    }

}
