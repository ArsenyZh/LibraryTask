FROM maven:3.8.4-openjdk-17 AS builder
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN mvn -f pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17-slim
COPY --from=builder target/library-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "library-service-0.0.1-SNAPSHOT.jar"]