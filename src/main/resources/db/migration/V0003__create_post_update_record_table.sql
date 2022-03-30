-- 新增帖文更新记录表
CREATE TABLE post_update_record
(
    post_id          text        NOT NULL,
    updated_type     text        NOT NULL,
    updater_id       text        NOT NULL,
    updated_at       timestamptz NOT NULL,
    reason           text,
    created_title    text,
    created_content  text,
    edited_title     text,
    un_edited_title   text,
    edited_content   text,
    un_edited_content text
);

-- 可能需要根据帖文对更新时间排序
CREATE INDEX post_update_record_post_id_and_updated_at_index ON post_update_record USING btree (post_id, updated_at);
