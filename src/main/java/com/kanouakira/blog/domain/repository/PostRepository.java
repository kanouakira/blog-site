package com.kanouakira.blog.domain.repository;

import com.kanouakira.blog.domain.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 帖文 jpa 持久层接口。
 *
 * @author KanouAkira
 * @date 2022/3/27 11:06
 */
public interface PostRepository extends JpaRepository<Post, String> {
}
