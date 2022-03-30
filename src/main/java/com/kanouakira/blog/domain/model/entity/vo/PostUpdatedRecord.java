package com.kanouakira.blog.domain.model.entity.vo;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * 帖文修改记录类。DDD 中的值对象。
 *
 * <p> 本博客中的帖文希望支持除作者外的成员发出修改申请，作者同意后则会保存到修改记录中。
 *
 * @author KanouAkira
 * @date 2022/3/28 22:30
 */
@Embeddable
public class PostUpdatedRecord {

    @Enumerated(EnumType.STRING)
    // 记录类型
    private UpdatedType updatedType;

    // 更新发起者
    private String updaterId;

    // 更新时间
    private OffsetDateTime updatedAt;

    // 更新原因
    private String reason;

    // 创建帖文标题
    private String createdTitle;

    // 创建帖文内容
    private String createdContent;

    // 更新后帖文标题
    private String editedTitle;

    // 更新前帖文标题
    private String unEditedTitle;

    // 更新后帖文内容
    private String editedContent;

    // 更新前帖文内容
    private String unEditedContent;

    /**
     * 获取一个创建帖文的记录，静态工厂提供防止构建出不符合要求的记录类。
     *
     * @param updaterId      创建者 id
     * @param createdTitle   创建帖文标题
     * @param createdContent 创建帖文内容
     * @return 创建记录
     */
    public static PostUpdatedRecord ofCreated(String updaterId, String createdTitle, String createdContent) {
        return new PostUpdatedRecord(UpdatedType.CREATED, 
                updaterId, OffsetDateTime.now(), 
                null, 
                createdTitle, 
                createdContent, 
                null, 
                null, 
                null,
                null);
    }

    /**
     * 获取一个更改帖文标题的记录。
     *
     * @param updaterId     更改者 id
     * @param reason        更改原因
     * @param editedTitle   更改后帖文标题
     * @param unEditedTitle 更改前帖文标题
     * @return 更新帖文标题记录
     */
    public static PostUpdatedRecord ofTitleEdited(String updaterId, String reason, String editedTitle, String unEditedTitle) {
        return new PostUpdatedRecord(UpdatedType.TITLE_EDITED,
                updaterId, 
                OffsetDateTime.now(),
                reason, 
                null, 
                null, 
                editedTitle, 
                unEditedTitle, 
                null,
                null);
    }

    /**
     * 获取一个更改帖文内容的记录。
     *
     * @param updaterId       更改者 id
     * @param reason          更改原因
     * @param editedContent   更改后帖文内容
     * @param unEditedContent 更改前帖文内容
     * @return 更新帖文内容记录
     */
    public static PostUpdatedRecord ofContentEdited(String updaterId, String reason, String editedContent, String unEditedContent) {
        return new PostUpdatedRecord(UpdatedType.CONTENT_EDITED,
                updaterId, 
                OffsetDateTime.now(),
                reason, 
                null, 
                null, 
                null, 
                null, 
                editedContent,
                unEditedContent);
    }

    @PersistenceConstructor
    protected PostUpdatedRecord() {
    }

    private PostUpdatedRecord(UpdatedType updatedType, String updaterId, OffsetDateTime updatedAt, String reason,
                              String createdTitle, String createdContent, String editedTitle, String unEditedTitle,
                              String editedContent, String unEditedContent) {
        this.updatedType = updatedType;
        this.updaterId = updaterId;
        this.updatedAt = updatedAt;
        this.reason = reason;
        this.createdTitle = createdTitle;
        this.createdContent = createdContent;
        this.editedTitle = editedTitle;
        this.unEditedTitle = unEditedTitle;
        this.editedContent = editedContent;
        this.unEditedContent = unEditedContent;
    }

    public enum UpdatedType {
        CREATED, TITLE_EDITED, CONTENT_EDITED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostUpdatedRecord that = (PostUpdatedRecord) o;
        return updatedType == that.updatedType 
                && Objects.equals(updaterId, that.updaterId) 
                && Objects.equals(updatedAt, that.updatedAt) 
                && Objects.equals(reason, that.reason) 
                && Objects.equals(createdTitle, that.createdTitle) 
                && Objects.equals(createdContent, that.createdContent) 
                && Objects.equals(editedTitle, that.editedTitle) 
                && Objects.equals(unEditedTitle, that.unEditedTitle) 
                && Objects.equals(editedContent, that.editedContent) 
                && Objects.equals(unEditedContent, that.unEditedContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updatedType, updaterId, updatedAt, reason, createdTitle, createdContent,
                editedTitle, unEditedTitle, editedContent, unEditedContent);
    }

    public UpdatedType getUpdatedType() {
        return updatedType;
    }

    public void setUpdatedType(UpdatedType updatedType) {
        this.updatedType = updatedType;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreatedTitle() {
        return createdTitle;
    }

    public void setCreatedTitle(String createdTitle) {
        this.createdTitle = createdTitle;
    }

    public String getCreatedContent() {
        return createdContent;
    }

    public void setCreatedContent(String createdContent) {
        this.createdContent = createdContent;
    }

    public String getEditedTitle() {
        return editedTitle;
    }

    public void setEditedTitle(String editedTitle) {
        this.editedTitle = editedTitle;
    }

    public String getUnEditedTitle() {
        return unEditedTitle;
    }

    public void setUnEditedTitle(String unEditedTitle) {
        this.unEditedTitle = unEditedTitle;
    }

    public String getEditedContent() {
        return editedContent;
    }

    public void setEditedContent(String editedContent) {
        this.editedContent = editedContent;
    }

    public String getUnEditedContent() {
        return unEditedContent;
    }

    public void setUnEditedContent(String unEditedContent) {
        this.unEditedContent = unEditedContent;
    }

}
