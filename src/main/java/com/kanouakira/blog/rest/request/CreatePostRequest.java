package com.kanouakira.blog.rest.request;

/**
 * 创建帖文的请求记录类。
 *
 * @author KanouAkira
 * @date 2022/3/27 23:56
 */
public record CreatePostRequest(
        String author,
        String title,
        String content
) {

}
