plugins {
    java
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "xyz.xichen"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("com.password4j:password4j:1.8.4")
    implementation("ch.qos.logback:logback-core:1.6.5")
    implementation("ch.qos.logback:logback-classic:1.6.5")
    implementation("org.slf4j:slf4j-api:2.0.20")
    testImplementation("org.springframework.boot:spring-boot-starter-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    
}

tasks.withType<Test> {
    useJUnitPlatform()
}
