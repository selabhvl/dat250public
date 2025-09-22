plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "no.hvl.data250"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("resetData", JavaExec::class) {
	dependsOn("classes")
	mainClass = "no.hvl.dat250.springjpa.ResetData"
	classpath = sourceSets.named("main").get().runtimeClasspath
	group = "experiment"
	description = "Resets the Bank account to the initial balance using the REST API"
}

tasks.register("runExperiment", JavaExec::class) {
	dependsOn("classes")
	mainClass = "no.hvl.dat250.springjpa.ConcurrentRequests"
	classpath = sourceSets.named("main").get().runtimeClasspath
	group = "experiment"
	description = "Runs the experiment by trying to execute two concurrent transfers."
}
