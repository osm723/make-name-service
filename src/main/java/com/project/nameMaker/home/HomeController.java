package com.project.nameMaker.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "/name/home";
    }

    @GetMapping("/a")
    public String a() {
        return "/name/header/header";
    }

    @GetMapping("/b")
    public String b() {
        return "/name/footer/footer";
    }
}
