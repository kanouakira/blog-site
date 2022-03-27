package com.kanouakira.blog.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

/**
 * 数据库测试配置类
 * <p>
 * 通过 testcontainers 依赖实现在测试环境 使用 docker 去创建一个数据库用于测试。
 *
 * @author KanouAkira
 * @date 2022/3/27 13:59
 */
public class DatabaseTestConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public PostgreSQLContainer<?> postgreSqlContainer() {
        return new PostgreSQLContainer<>("postgres:14-alpine");
    }

    @Bean
    @FlywayDataSource
    public DataSource dataSource(PostgreSQLContainer<?> postgreSQLContainer) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(postgreSQLContainer.getJdbcUrl());
        hikariConfig.setUsername(postgreSQLContainer.getUsername());
        hikariConfig.setPassword(postgreSQLContainer.getPassword());
        return new HikariDataSource(hikariConfig);
    }

}
