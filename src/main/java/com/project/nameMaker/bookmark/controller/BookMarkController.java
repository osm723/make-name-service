package com.project.nameMaker.bookmark.controller;

import com.project.nameMaker.utils.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String bookMarkSave(String addName, Model model, HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.setCookie(addName, request, response);
        model.addAttribute("addName", addName);
        return "redirect:/bookMark/main";
    }

    @GetMapping("/bookMark/remove")
    public String bookMarkRemove(HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.removeCookie(request, response);
        return "redirect:/bookMark/main";
    }


}
