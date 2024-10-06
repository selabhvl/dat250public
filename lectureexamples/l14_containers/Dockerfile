# Do compilation inside a (throw-away) builder container
FROM gradle:8-jdk21 AS builder

# working directory inside the container is implicitly /home/gradle, but we make it explicit here
WORKDIR /home/gradle

# copy only necessary source code files
COPY settings.gradle.kts gradlew build.gradle.kts .
# Copying a folder recursively need special attention
COPY src src
COPY gradle gradle

# compile with gradle
RUN ./gradlew bootJar

# rename and move the resulting JAR file
RUN mv build/libs/l14-0.0.1-SNAPSHOT.jar app.jar


# This will be the base image for the running application
FROM eclipse-temurin:21-alpine

# creating a new user to avoid running the app as root
RUN addgroup -g 1000 app
RUN adduser -G app -D -u 1000 -h /app app

# switch into the newly created user and directory
USER app
WORKDIR /app

# copy the app from the builder image
COPY --from=builder --chown=1000:1000 /home/gradle/app.jar . 

# Start the Spring Boot app when the container starts
CMD ["java", "-jar", "app.jar"]
