package com.kanouakira.blog.rest;

import com.kanouakira.blog.domain.application.PostApplicationService;
import com.kanouakira.blog.domain.application.command.CreatePostCommand;
import com.kanouakira.blog.domain.application.result.PostCreatedResult;
import com.kanouakira.blog.rest.request.CreatePostRequest;
import com.kanouakira.blog.rest.response.PostCreatedResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 帖文的 rest 表现层。
 *
 * <p>所有通过 domain 的应用服务来调用的逻辑都属于写逻辑即命令。
 *
 * @author KanouAkira
 * @date 2022/3/27 23:51
 */
@RestController
@RequestMapping("/posts")
public class PostCommandRestController {

    private final PostApplicationService postApplicationService;

    public PostCommandRestController(PostApplicationService postApplicationService) {
        this.postApplicationService = postApplicationService;
    }

    @PostMapping("/create-post")
    public PostCreatedResponse createPost(@RequestBody CreatePostRequest request) {
        // 根据请求中携带的内容，或者 url 中或 body 中的内容构造创建帖文的命令。
        CreatePostCommand createPostCommand = new CreatePostCommand(request.author(), request.title(), request.content());
        PostCreatedResult result = postApplicationService.createPost(createPostCommand);
        return new PostCreatedResponse(result.postId());
    }

}
