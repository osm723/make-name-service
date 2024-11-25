package com.project.nameMaker.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CookieUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String SAVED_NAME_COOKIE = "savedName";

    @GetMapping("/set-cookie")
    public void setCookie(String saveName, HttpServletRequest request, HttpServletResponse response) {
        List<String> savedNames = getCookie(request);
        savedNames.add(savedNames.size(), saveName);

        try {
            String json = objectMapper.writeValueAsString(savedNames);
            String encodedValue = URLEncoder.encode(json, StandardCharsets.UTF_8);

            Cookie cookie = new Cookie(SAVED_NAME_COOKIE, encodedValue);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60); // 쿠키 유효기간 설정 (예: 7일)
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/get-cookie")
    public List<String> getCookie(HttpServletRequest request) {
        List<String> savedNames = new ArrayList<>();
        if (request.getCookies() != null) {
            Cookie namesCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> SAVED_NAME_COOKIE.equals(cookie.getName()))
                    .findFirst()
                    .orElse(null);
            if (namesCookie != null) {
                try {
                    savedNames = objectMapper.readValue(URLDecoder.decode(namesCookie.getValue()), List.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return savedNames;
    }

    @GetMapping("/remove-cookie")
    public void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie namesCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> SAVED_NAME_COOKIE.equals(cookie.getName()))
                .findFirst()
                .orElse(null);

        if (namesCookie != null) {
            namesCookie.setPath("/");  // 쿠키의 경로를 설정 (생성 시의 경로와 일치해야 함)
            namesCookie.setMaxAge(0);  // 만료 시간 0으로 설정하여 삭제
            response.addCookie(namesCookie);
        }
    }
}
