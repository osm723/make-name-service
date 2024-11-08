package com.project.nameMaker.generation.controller;

import com.project.nameMaker.generation.service.GenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/generation")
public class GenerationController {

    private final GenerationService generationService;

    @GetMapping("/main")
    public String generationMain(Model model) {
        //List<String> nameGeneration = generationService.nameGeneration(firstName);
        model.addAttribute("names", "");
        return "/name/generation/generationMain";
    }

    @GetMapping("/genName")
    public String generationName(Model model, String firstName) {
        List<String> nameGeneration = generationService.nameGeneration(firstName);
        model.addAttribute("names", nameGeneration);
        model.addAttribute("firstName", firstName);
        return "/name/generation/generationMain";
    }
}
