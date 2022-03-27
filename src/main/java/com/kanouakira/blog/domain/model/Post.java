package com.kanouakira.blog.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 帖文实体类，最基本地实现。
 *
 * @author KanouAkira
 * @date 2022/3/27 10:48
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    // 主键
    private String id;

    // 帖文作者
    private String author;

    // 帖文标题
    private String title;

    // 帖文内容
    private String content;

    /* 提供给 spring 反射使用的构造函数，确保我们自己不会去使用，声明 protected */
    protected Post() {
    }

    /* 声明构造函数，无需id */
    public Post(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
