### Introduction
This project is a simple Spring Boot application that accesses a Cassandra cluster. The goal
of the project is to demonstrate the integration of Spring Boot and Cassandra in the scope of
Docker containers.

The project's base directory will be referred to as $basedir from now on.

**Requirements:**

- Docker

### Docker
It contains a Dockerfile for the application in the root path and a Dockerfile for Cassandra 
in the cassandra folder that can be found in the root path.

#### Cassandra Docker image

**Building**

    cd $basedir/cassandra
    docker build -t picadoh/cassandra .

**Running**

    cd $basedir/cassandra
    docker run -d --name cassandra -p 9042:9042 picadoh/cassandra

Please remove the -d option to run in the foreground.

#### Spring Boot Docker image

**Building**

    cd $basedir
    docker build -t picadoh/boots

**Running**

    docker run --name boots --link cassandra:db -p 8080:8080 picadoh/boots

Note the links is used to make sure a DB_PORT_9042_TCP_ADDR will be set so it can be read by
the Spring Boot application in order to access the Cassandra DB.

### Testing
After starting Cassandra and the Spring Boot application on the corresponding containers
you may access the application through _docker_:8080, being _docker_ the Docker IP.

You may see a blank page, see section below to add data directly by connecting to the
Cassandra container.

### Inserting data directly on Cassandra

    docker exec -it cassandra bash
    cassandra$ ./bin/cqlsh
    cqlsh$ USE sampleks;
    cqlsh$ INSERT INTO person (id, name) values ('XYZ123', 'John');
    cqlsh$ INSERT INTO person (id, name) values ('ZYX567', 'Anna');

### Troubleshooting

> Cannot connect to the Docker daemon. Is the docker daemon running on this host?

If you are experiencing this error, you might have forgotten to load docker's environement 
variables in the current terminal session. You might do so with the following command:

    eval $(docker-machine env)

