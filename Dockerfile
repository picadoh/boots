FROM maven:3-openjdk-8 as builder

WORKDIR /app

ADD pom.xml /app
RUN mvn dependency:resolve
ADD . /app
RUN mvn package

FROM openjdk:8

RUN mkdir -p /app
COPY --from=builder /app/target/boots-1.0-SNAPSHOT.jar /app/boots.jar
ENTRYPOINT ["java","-jar","/app/boots.jar"]
