-- V0001 中声明帖文的主键为 serial ，因此 PostgreSQL 会使用 int 或者 bigint 来表示，
-- 而实体类中声明 id 为 String 类型，所以 JPA 做查询时会抛出异常。
-- 考虑到字符类型比较兼容，此次修改帖文表的主键字段类型。
DROP TABLE IF EXISTS post;

CREATE SEQUENCE post_id_seq;

CREATE TABLE post
(
    id      text PRIMARY KEY DEFAULT lpad(nextval('post_id_seq')::text, 10, '0'),
    author  text NOT NULL,
    title   text NOT NULL,
    content text
);

-- 声明序列属于 post 的 id 字段，后续对该字段做修改或是删除表，序列同时删除。
ALTER SEQUENCE post_id_seq OWNED BY post.id;

-- 可能会通过作者来筛选创建索引，这里使用 hash 实现，因为没有排序需求，否则使用 btree。
CREATE INDEX post_author_index ON post USING hash(author);