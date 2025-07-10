package com.example.githubactiontest;

import com.example.githubactiontest.controller.TestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubactionTestApplicationTests {

    @Autowired
    private TestController testController;

    @LocalServerPort
    private int port;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // MockMvc 설정 (애플리케이션 컨텍스트를 통해 서버 없이 HTTP 요청을 테스트)
        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/"))  // "/" URL에 GET 요청 보내기
                .andExpect(status().isOk())  // HTTP 상태 코드 200 (OK)
                .andExpect(content().string("Hello World"));  // 응답 내용이 "Hello World"인지 확인
    }

    @Test
    public void testHealthz() throws Exception {
        mockMvc.perform(get("/healthz"))  // "/healthz" URL에 GET 요청 보내기
                .andExpect(status().isOk())  // HTTP 상태 코드 200 (OK)
                .andExpect(content().string("healthz"));  // 응답 내용이 "healthz"인지 확인
    }

    // 틀린 코드
//    @Test
//    public void testHealthz_fail() throws Exception {
//        mockMvc.perform(get("/healthz"))  // "/healthz" URL에 GET 요청 보내기
//                .andExpect(status().isOk())  // HTTP 상태 코드 200 (OK)
//                .andExpect(content().string("hi"));  // 일부러 틀린 코드
//    }

}
