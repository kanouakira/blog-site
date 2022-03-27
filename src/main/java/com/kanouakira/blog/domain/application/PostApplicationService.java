package com.kanouakira.blog.domain.application;

import com.kanouakira.blog.domain.application.command.CreatePostCommand;
import com.kanouakira.blog.domain.application.result.PostCreatedResult;
import com.kanouakira.blog.domain.model.Post;
import com.kanouakira.blog.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 帖文应用服务层。
 *
 * <p>此处不宜过多业务逻辑，所有函数的实现应当都是很简洁的。
 *
 * <p>如果业务有更复杂逻辑需要实现，要么应该放在聚合根（例如 Post ）中、或者将引入 domain service（领域服务）来帮助实现复杂业务逻辑。
 *
 * @author KanouAkira
 * @date 2022/3/27 23:19
 */
@Service
@Transactional
public class PostApplicationService {

    private final PostRepository postRepository;

    public PostApplicationService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostCreatedResult createPost(CreatePostCommand command) {
        Post post = new Post(command.author(), command.title(), command.content());
        postRepository.save(post);
        return new PostCreatedResult(post.getId());
    }

}
