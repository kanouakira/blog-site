package com.kanouakira.blog.domain.model.entity;

import com.kanouakira.blog.domain.model.entity.vo.PostUpdatedRecord;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    // 帖文作者 id
    private String authorId;

    // 帖文标题
    private String title;

    // 帖文内容
    private String content;

    @ElementCollection
    @CollectionTable(name = "post_update_record")
    @OrderBy("updatedAt")
    // 帖文的修改记录
    private List<PostUpdatedRecord> updatedRecords;

    /* 提供给 spring 反射使用的构造函数，确保我们自己不会去使用，声明 protected */
    @PersistenceConstructor
    protected Post() {
    }

    /* 声明构造函数，无需id */
    public Post(String authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.updatedRecords = new ArrayList<>();
        updatedRecords.add(PostUpdatedRecord.ofCreated(authorId, title, content));
    }

    public void editTitle(String editorId, String reason, String title) {
        updatedRecords.add(PostUpdatedRecord.ofTitleEdited(editorId, reason, title, this.title));
        this.title = title;
    }

    public void editContent(String editorId, String reason, String content) {
        updatedRecords.add(PostUpdatedRecord.ofContentEdited(editorId, reason, content, this.content));
        this.content = content;
    }

    public List<PostUpdatedRecord> getUpdatedRecords() {
        // 直接返回给外部的话还有可能被直接操作增加删除破坏实体约束，所以返回不可修改的记录集合。
        return Collections.unmodifiableList(updatedRecords);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String author) {
        this.authorId = author;
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
