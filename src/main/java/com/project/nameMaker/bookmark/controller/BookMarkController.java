package com.project.nameMaker.bookmark.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookMarkController {

    private final CookieUtils cookieUtils;

    @GetMapping("/bookMark/main")
    public String bookMarkMain(Model model, HttpServletRequest request) {
        List<String> savedNames = cookieUtils.getCookie(request);
        model.addAttribute("names", savedNames);

        return "/name/bookMark/bookmarkMain";
    }

    @GetMapping("/bookMark/save")
    public String bookMarkSave(String saveName, Model model, HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.setCookie(saveName, request, response);
        model.addAttribute("saveName", saveName);
        return "redirect:/bookMark/main";
    }

    @GetMapping("/bookMark/remove")
    public String bookMarkRemove(HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.removeCookie(request, response);
        return "redirect:/bookMark/main";
    }

    @PostMapping("/api/bookMark/save")
    public ResponseEntity<String> bookMarkSaveApi(@RequestParam String saveName, Model model, HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.setCookie(saveName, request, response);
        model.addAttribute("saveName", saveName);

        return ResponseEntity.ok("저장했습니다.");
    }

}
