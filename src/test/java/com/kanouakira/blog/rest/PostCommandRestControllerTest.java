package com.kanouakira.blog.rest;

import com.kanouakira.blog.domain.application.PostApplicationService;
import com.kanouakira.blog.domain.application.result.PostCreatedResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 帖文 rest 表现层测试类。
 *
 * <p>通过 @WebMvcTest 注解可以在 Spring Mvc 测试时只关注我们的 Mvc 组件。
 *
 * @author KanouAkira
 * @date 2022/3/28 00:01
 */
@WebMvcTest
class PostCommandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // 由于 @WebMvcTest 只启动了 Web Mvc 这一层的 bean。
    // 但是我们的表现层还依赖 domain 中的应用服务，所以通过 @MockBean 将所需的 bean 注入。
    @MockBean
    private PostApplicationService postApplicationService;

    @Test
    void should_return_ok_when_create_post() throws Exception {
        String postId = "1";
        given(postApplicationService.createPost(any())).willReturn(new PostCreatedResult(postId));
        // 读取请求体
        byte[] requestBody = new ClassPathResource("request/post/create-post/200-ok.json").getInputStream().readAllBytes();
        mockMvc
                .perform(
                        post("/posts/create-post")
                                .contentType(APPLICATION_JSON)
                                .content(requestBody)
                )
                // 断言请求状态码返回 200。
                .andExpect(status().isOk())
                // 断言返回 jsonPath 中的 postId 为 1。
                .andExpect(jsonPath("$.postId").value(postId));
    }

}