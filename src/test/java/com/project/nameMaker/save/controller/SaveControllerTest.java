package com.project.nameMaker.save.controller;

import com.project.nameMaker.common.utils.CookieUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaveController.class)
class SaveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CookieUtils cookieUtils;

    @MockBean
    private MockHttpServletRequest request;

    @MockBean
    private MockHttpServletResponse response;

    @Test
    void saveMain() throws Exception {
        mockMvc.perform(get("/save/main")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()); // HTTP 상태 코드 검증
    }

    @Test
    void saveName() throws Exception {
        mockMvc.perform(get("/save/saveName")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isFound()); // HTTP 상태 코드 검증
    }

    @Test
    void removeNames() throws Exception {
        mockMvc.perform(get("/save/removeName")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isFound()); // HTTP 상태 코드 검증
    }

    @Test
    void saveNameApi() throws Exception {
        String saveName = "오수아";

        mockMvc.perform(post("/save/api/saveName?saveName="+saveName)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()); // HTTP 상태 코드 검증
    }
}