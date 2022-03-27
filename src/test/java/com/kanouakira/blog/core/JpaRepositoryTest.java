package com.kanouakira.blog.core;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * 自定义 jpa 持久层测试注解。
 *
 * <p> 测试时引入自定义数据源测试配置，通过 testcontainers 以容器来运行测试数据库。 并且要声明防止 spring 自动配置测试数据源。
 *
 * @author KanouAkira
 * @date 2022/3/27 14:38
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DataJpaTest
@Import(DatabaseTestConfiguration.class)
@AutoConfigureTestDatabase(replace = NONE)
public @interface JpaRepositoryTest {

}
