FROM maven:3-jdk-8 as builder

RUN mkdir /app
ADD . /app
WORKDIR /app
RUN mvn clean install

FROM java:8

RUN mkdir -p /app
COPY --from=builder /app/target/boots-1.0-SNAPSHOT.jar /app/boots.jar
RUN sh -c 'touch /app/boots.jar'
ENTRYPOINT ["java","-jar","/app/boots.jar"]
