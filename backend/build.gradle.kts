plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.fileshare"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

val mockkVersion = "1.13.12"
val springMockkVersion = "4.0.2"
val openapiVersion = "2.3.0"
val logStashVersion = "8.0"
val wiremockVersion = "3.9.1"
val jsonPatchVersion = "1.13"
val awsSdkVersion = "2.26.25"
val hibernateUtilsVersion = "3.8.2"
val kotlinCoroutinesVersion = "1.9.0-RC"

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator") // metrics
	implementation("org.springframework.boot:spring-boot-starter-webflux") // Http Client
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.github.java-json-tools:json-patch:$jsonPatchVersion")

	// validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Logs
	implementation("net.logstash.logback:logstash-logback-encoder:$logStashVersion")

	// doc - swagger-openapi
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openapiVersion")

	// DB
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.flywaydb:flyway-core")
	implementation("io.hypersistence:hypersistence-utils-hibernate-63:$hibernateUtilsVersion") // json type
	runtimeOnly("org.postgresql:postgresql")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
	}
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("io.mockk:mockk:$mockkVersion")
	testImplementation("com.ninja-squad:springmockk:$springMockkVersion")
	testImplementation("org.wiremock:wiremock-standalone:$wiremockVersion")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
