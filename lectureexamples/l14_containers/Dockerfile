FROM gradle:8-jdk21 AS builder

COPY . .

RUN gradle bootJar

RUN mv build/libs/l14-0.0.1-SNAPSHOT.jar app.jar


FROM eclipse-temurin:21-alpine

COPY --from=builder /home/gradle/app.jar . 


CMD ["java", "-jar", "app.jar"]
