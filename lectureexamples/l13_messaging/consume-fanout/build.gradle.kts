
plugins {
    application
    idea
}

application {
    mainClass = "no.hvl.dat250.messaging.FanoutConsumer"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}


idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
