### Introduction

This project is a simple Spring Boot application that accesses a Cassandra cluster.

**Requirements:**

- Docker
- Java 21

### Start Cassandra

```shell
docker-compose up -d
```

### Start the application

```shell
./mvnw spring-boot:run
```

Access http://localhost:8080 to see the data just inserted into the database.

### Local data setup

The table is pre-populated locally once the application starts. To manipulate the data, it can
be done via `cqlsh` as follows:

```shell
docker exec -it boots-cassandra-1 bash
root# cqlsh
cqlsh$ INSERT INTO localks.people (id, name) values ('FOO123', 'Jane');
cqlsh$ INSERT INTO localks.people (id, name) values ('BAR567', 'Joe');
```
