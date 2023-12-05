plugins {
    kotlin("jvm") version "1.9.21"
    id("org.springframework.boot") version "3.2.0"
}


apply {
    plugin("io.spring.dependency-management")
}

group = "com.github.scroogemcfawk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")

    testImplementation(platform("org.junit:junit-bom"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
