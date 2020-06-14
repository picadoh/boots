### Introduction

This project is a simple Spring Boot application that accesses a Cassandra cluster.<br/>
The goal of the project is to demonstrate the integration of Spring Boot and Cassandra<br/>
in the scope of Docker containers.

**Requirements:**

- Docker

### Docker

The application Dockerfile is at the root path. It contains the instructions for building<br/>
the Docker image. This example does not use `docker-compose`, containers are glue together<br/>
using the `--link` flag.

#### Application Docker image

**Building**

    docker build -t boots_app .

**Running**

    docker run -d --name cassandra -p 9042:9042 cassandra
    docker run --rm --name boots_app --link cassandra:db -p 8080:8080 boots_app

### Testing

**Inserting data directly on Cassandra**

    docker exec -it cassandra bash
    cassandra$ cqlsh
    cqlsh$ USE sampleks;
    cqlsh$ INSERT INTO person (id, name) values ('XYZ123', 'John');
    cqlsh$ INSERT INTO person (id, name) values ('ZYX567', 'Anna');

Access http://localhost:8080 to see the data just inserted into the database.<br/>
If using `docker-machine` use the Docker Machine IP instead of localhost.

### Troubleshooting

> Cannot connect to the Docker daemon. Is the docker daemon running on this host?

If you are experiencing this error using `docker-machine`, you might have forgotten<br/>
to load docker's environment variables in the current terminal session. You might do<br/>
so with the following command:

    eval $(docker-machine env)
