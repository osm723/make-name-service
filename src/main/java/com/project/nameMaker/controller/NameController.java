package com.project.nameMaker.controller;

import com.project.nameMaker.dto.NameRequestCond;
import com.project.nameMaker.dto.NameResponseDto;
import com.project.nameMaker.service.NameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nameStats")
public class NameController {

    private final NameService nameService;

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

    /**
     * 전체 조회
     * @param pageable
     * @return Page<NameResponseDto>
     */
    @GetMapping("/namesAll")
    public Page<NameResponseDto> namesAll(Pageable pageable) {
        return nameService.findAll(pageable);
    }

    /**
     * 조건부 조회
     * @param pageable
     * @param nameRequestCond
     * @return Page<NameResponseDto>
     */
    @GetMapping("/namesSearch")
    public Page<NameResponseDto> namesSearch(Pageable pageable, NameRequestCond nameRequestCond) {
        return nameService.findByWhere(pageable, nameRequestCond);
    }

}
