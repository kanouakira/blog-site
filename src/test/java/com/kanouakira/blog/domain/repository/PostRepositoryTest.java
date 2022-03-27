package com.kanouakira.blog.domain.repository;

import com.kanouakira.blog.core.JpaRepositoryTest;
import com.kanouakira.blog.domain.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * 帖文持久层测试类。
 *
 * @author KanouAkira
 * @date 2022/3/27 11:09
 */
@JpaRepositoryTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void should_repository_successfully_save_post() {
        Post post = new Post("KanouAkira", "A test title", "A test content");
        Post savedPost = postRepository.save(post);
        assertThat(savedPost.getId(), is(notNullValue()));
        assertThat(savedPost.getAuthor(), equalTo(post.getAuthor()));
        assertThat(savedPost.getTitle(), equalTo(post.getTitle()));
        assertThat(savedPost.getContent(), equalTo(post.getContent()));
    }

}