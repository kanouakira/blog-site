package com.kanouakira.blog.domain.application.command;

/**
 * 创建帖文命令记录类。
 *
 * @author KanouAkira
 * @date 2022/3/27 23:22
 */
public record CreatePostCommand(
        String author,
        String title,
        String content
) {

}
