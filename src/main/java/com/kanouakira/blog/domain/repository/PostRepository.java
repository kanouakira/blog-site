package com.kanouakira.blog.domain.repository;

import com.kanouakira.blog.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author KanouAkira
 * @date 2022/3/27 11:06
 */
public interface PostRepository extends JpaRepository<Post, String> {
}
