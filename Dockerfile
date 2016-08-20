FROM java:8
VOLUME /tmp
ADD target/boots-1.0-SNAPSHOT.jar boots.jar
RUN sh -c 'touch /boots.jar'
ENTRYPOINT ["java","-jar","/boots.jar"]
