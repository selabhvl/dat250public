plugins {
  `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.4")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.3")
    testImplementation("junit:junit:4.12")
}
