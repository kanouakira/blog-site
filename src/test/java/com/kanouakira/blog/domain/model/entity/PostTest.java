package com.kanouakira.blog.domain.model.entity;

import com.kanouakira.blog.domain.model.vo.PostUpdatedRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kanouakira.blog.domain.model.vo.PostUpdatedRecord.UpdatedType.CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * 聚合根帖文测试。
 *
 * @author KanouAkira
 * @date 2022/3/29 19:35
 */
class PostTest {

    @Test
    void should_generate_created_record_after_post_created() {
        Post post = new Post("KanouAkira", "A test title", "A test content");
        List<PostUpdatedRecord> updatedRecords = post.getUpdatedRecords();
        assertThat(updatedRecords, hasSize(1));
        PostUpdatedRecord postUpdatedRecord = updatedRecords.get(0);
        assertThat(postUpdatedRecord.getUpdatedType(), is(CREATED));
        assertThat(postUpdatedRecord.getCreatedTitle(), is("A test title"));
        assertThat(postUpdatedRecord.getUpdaterId(), is("KanouAkira"));
        assertThat(postUpdatedRecord.getCreatedContent(), is("A test content"));
    }

    @Test
    void should_generate_edited_record_after_post_edited() {
        String testTitle = "A test title";
        String testContent = "A test content";
        Post post = new Post("KanouAkira", testTitle, testContent);
        String editedTitle = "A edited title";
        String reason = "for test";
        post.editTitle("Editor1", reason, editedTitle);
        assertThat(post.getTitle(), is(editedTitle));
        String editedContent = "A edited content";
        post.editContent("Editor2", reason, editedContent);
        assertThat(post.getContent(), is(editedContent));
        List<PostUpdatedRecord> updatedRecords = post.getUpdatedRecords();
        assertThat(updatedRecords, hasSize(3));
        PostUpdatedRecord createdRecord = updatedRecords.get(0);
        assertThat(createdRecord.getUpdatedType(), is(CREATED));
        assertThat(createdRecord.getCreatedTitle(), is(testTitle));
        assertThat(createdRecord.getUpdaterId(), is("KanouAkira"));
        assertThat(createdRecord.getCreatedContent(), is(testContent));
        PostUpdatedRecord titleEditedRecord = updatedRecords.get(1);
        assertThat(titleEditedRecord.getReason(), is(reason));
        assertThat(titleEditedRecord.getEditedTitle(), is(editedTitle));
        assertThat(titleEditedRecord.getUnEditedTitle(), is(testTitle));
        assertThat(titleEditedRecord.getUpdaterId(), is("Editor1"));
        PostUpdatedRecord contentEditedRecord = updatedRecords.get(2);
        assertThat(contentEditedRecord.getReason(), is(reason));
        assertThat(contentEditedRecord.getEditedContent(), is(editedContent));
        assertThat(contentEditedRecord.getUnEditedContent(), is(testContent));
        assertThat(contentEditedRecord.getUpdaterId(), is("Editor2"));
    }

}