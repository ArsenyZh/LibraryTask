FROM maven:3.8.4-openjdk-17 AS builder
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN mvn -f pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17-slim
COPY --from=builder target/book-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "book-service-0.0.1-SNAPSHOT.jar"]