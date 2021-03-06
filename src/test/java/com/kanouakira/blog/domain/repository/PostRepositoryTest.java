package com.kanouakira.blog.domain.repository;

import com.kanouakira.blog.core.JpaRepositoryTest;
import com.kanouakira.blog.domain.model.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * 帖文持久层测试类。post
 *
 * @author KanouAkira
 * @date 2022/3/27 11:09
 */
@JpaRepositoryTest
class PostRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Test
    void should_repository_successfully_save_post() {
        Post post = new Post("KanouAkira", "A test title", "A test content");
        post.editContent("Editor1", "for test edit content", "A edited content");
        post.editTitle("Editor2", "for test edit title", "A edited title");
        Post savedPost = postRepository.save(post);
        assertThat(savedPost.getId(), is(notNullValue()));
        assertPostEqual(savedPost, post);
    }

    @Test
    void should_repository_find_post_after_created() {
        Post post = new Post("KanouAkira", "A test title", "A test content");
        post.editContent("Editor1", "for test edit content", "A edited content");
        post.editTitle("Editor2", "for test edit title", "A edited title");
        Post savedPost = postRepository.saveAndFlush(post);
        entityManager.detach(savedPost);
        Post findPost = postRepository.findById(post.getId()).orElseThrow(AssertionError::new);
        assertPostEqual(findPost, savedPost);
    }

    private void assertPostEqual(Post actualPost, Post expectPost) {
        assertThat(actualPost.getAuthorId(), equalTo(expectPost.getAuthorId()));
        assertThat(actualPost.getTitle(), equalTo(expectPost.getTitle()));
        assertThat(actualPost.getContent(), equalTo(expectPost.getContent()));
        assertThat(actualPost.getUpdatedRecords(), hasSize(expectPost.getUpdatedRecords().size()));
        for (int i = 0; i < actualPost.getUpdatedRecords().size(); i++) {
            assertThat(actualPost.getUpdatedRecords().get(i), equalTo(expectPost.getUpdatedRecords().get(i)));
        }
    }

}