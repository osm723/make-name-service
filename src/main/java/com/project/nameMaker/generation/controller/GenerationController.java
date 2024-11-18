package com.project.nameMaker.generation.controller;

import com.project.nameMaker.generation.dto.GenerationRequestDto;
import com.project.nameMaker.generation.service.GenerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/generation")
@Slf4j
public class GenerationController {

    private final GenerationService generationService;

    @GetMapping("/main")
    public String generationMain(Model model) {
        setModel(model, new GenerationRequestDto());
        return "/name/generation/generationMain";
    }

    @GetMapping("/generationNames")
    public String generationNames(Model model, GenerationRequestDto generationRequestDto) {
        List<String> generationNames = generationService.generationNames(generationRequestDto);
        setModel(model, generationRequestDto);
        model.addAttribute("names", generationNames);

        return "/name/generation/generationMain";
    }

    private void setModel(Model model, GenerationRequestDto generationRequestDto) {
        model.addAttribute("selectedGender", generationRequestDto.getGender() != null ? generationRequestDto.getGender() : "");
        model.addAttribute("selectedName", generationRequestDto.getSelectedName() != null ? generationRequestDto.getSelectedName() : "");
        model.addAttribute("lastName", generationRequestDto.getLastName());
        model.addAttribute("firstName", generationRequestDto.getFirstName());
        model.addAttribute("secondName", generationRequestDto.getSecondName());
    }
}
