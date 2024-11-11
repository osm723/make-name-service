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
        //List<String> nameGeneration = generationService.nameGeneration(firstName);
        model.addAttribute("names", "");
        return "/name/generation/generationMain";
    }

    @GetMapping("/genName")
    public String generationName(Model model, GenerationRequestDto generationRequestDto) {
        List<String> nameGeneration = generationService.nameGeneration(generationRequestDto);

        model.addAttribute("names", nameGeneration);
        model.addAttribute("lastName", generationRequestDto.getLastName());
        model.addAttribute("firstName", generationRequestDto.getFirstName());
        model.addAttribute("secondName", generationRequestDto.getSecondName());
        return "/name/generation/generationMain";
    }
}
