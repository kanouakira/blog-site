-- 最基本的帖文应当具有的属性。
-- id      帖文的有序主键
-- author  帖文作者
-- title   帖文标题
-- content 帖文内容
CREATE TABLE post
(
    id      serial PRIMARY KEY,
    author  text NOT NULL,
    title   text NOT NULL,
    content text
)