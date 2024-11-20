package com.project.nameMaker.save.controller;

import com.project.nameMaker.utils.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/save")
public class SaveController {

    private final CookieUtils cookieUtils;

    @GetMapping("/main")
    public String saveMain(Model model, HttpServletRequest request) {
        List<String> savedNames = cookieUtils.getCookie(request);
        model.addAttribute("names", savedNames);

        return "/name/save/saveMain";
    }

    @GetMapping("/saveName")
    public String saveName(String saveName, Model model, HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.setCookie(saveName, request, response);
        model.addAttribute("saveName", saveName);
        return "redirect:/save/main";
    }

    @GetMapping("/removeName")
    public String removeNames(HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.removeCookie(request, response);
        return "redirect:/save/main";
    }

    @PostMapping("/api/saveName")
    public ResponseEntity<String> bookMarkSaveApi(@RequestParam String saveName, Model model, HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.setCookie(saveName, request, response);
        model.addAttribute("saveName", saveName);

        return ResponseEntity.ok("저장했습니다.");
    }

}
