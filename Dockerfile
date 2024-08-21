FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /app

ADD pom.xml /app
RUN mvn dependency:resolve
ADD . /app
RUN mvn package -DskipTests

FROM openjdk:21

RUN mkdir -p /app
COPY --from=builder /app/target/boots-1.0-SNAPSHOT.jar /app/boots.jar
ENTRYPOINT ["java","-jar","/app/boots.jar"]
