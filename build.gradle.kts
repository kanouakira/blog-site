import org.springframework.boot.gradle.plugin.SpringBootPlugin.*

plugins {
    java
    checkstyle
    id("org.springframework.boot") version "2.6.5"
}

/* TDD 测试驱动开发，第一步引入测试 */
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    /* 通过 gradle Bom 来控制 Spring Boot 版本 */
    implementation(platform(BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    /* 引入数据源 */
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.flywaydb:flyway-core")

    /* 测试依赖 */
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers:1.16.3")
    testImplementation("org.testcontainers:postgresql:1.16.3")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

/* 代码风格检验 */
checkstyle{
    maxWarnings = 0
    toolVersion = "10.0"
}

tasks {
    test {
        useJUnitPlatform()
    }
}