package com.project.nameMaker.generation.controller;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.service.GenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/generation")
public class GenerationController {

    private final GenerationService generationService;

    /**
     * generationMain
     * 이름 생성 초기화면
     * @param generationRequestDto
     * @param model
     * @return viewPath
     */
    @GetMapping("/main")
    public String generationMain(@ModelAttribute("generationName") GenerationRequestDto generationRequestDto, Model model) {
        setModel(model, generationRequestDto);
        return "/name/generation/generationMain";
    }

    /**
     * generationNames
     * 이름 생성 기능
     * @param generationRequestDto
     * @param model
     * @return viewPath
     */
    @GetMapping("/generationNames")
    public String generationNames(@ModelAttribute("generationName") GenerationRequestDto generationRequestDto, Model model) {
        List<String> generationNames = generationService.generationNames(generationRequestDto);
        setModel(model, generationRequestDto);
        model.addAttribute("names", generationNames);

        return "/name/generation/generationMain";
    }

    /**
     * setModel
     * model에 selectedName 조건 등록
     * @param model
     */
    private void setModel(Model model, GenerationRequestDto generationRequestDto) {
        model.addAttribute("selectedName", generationRequestDto.getSelectedName() != null ? generationRequestDto.getSelectedName() : "");
    }
}
