

plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    // Main dependency
    implementation("org.hibernate.orm:hibernate-core:7.1.1.Final")
    // Drivers
    implementation("com.h2database:h2:2.3.232")
    implementation("org.postgresql:postgresql:42.7.7")

    // Support for compile-time generation of queries
    annotationProcessor("org.hibernate.orm:hibernate-processor:7.1.1.Final")

    testImplementation("org.junit.jupiter:junit-jupiter:5.13.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")



}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType(JavaCompile::class) {
    options.generatedSourceOutputDirectory = file("src/generated/java")
}
tasks.register("demo1", JavaExec::class) {
    dependsOn("classes")
    mainClass = "no.hvl.dat250.jpa.Demo1App"
    classpath = sourceSets.named("main").get().runtimeClasspath
    group = "run"
    description = "First Demo Application: Connects to a Postgres instance"
}

tasks.register("demo2", JavaExec::class) {
    dependsOn("classes")
    mainClass = "no.hvl.dat250.jpa.Demo2App"
    classpath = sourceSets.named("main").get().runtimeClasspath
    group = "run"
    description = "Second Demo Application: Uses H2 database to create schema from scratch"
}

tasks.register("demo3", JavaExec::class) {
    dependsOn("classes")
    mainClass = "no.hvl.dat250.jpa.Demo3App"
    classpath = sourceSets.named("main").get().runtimeClasspath
    group = "run"
    description = "Third Demo Application: Demonstrates the use of inheritance in ORM"
}

tasks.register("demo4", JavaExec::class) {
    dependsOn("classes")
    mainClass = "no.hvl.dat250.jpa.Demo4App"
    classpath = sourceSets.named("main").get().runtimeClasspath
    group = "run"
    description = "Fourth Demo Application: Demonstrates the use of Transactions and Locks (Lecture 10)"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
