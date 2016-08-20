package com.github.picadoh.examples.boots;

import com.github.picadoh.examples.boots.cassandra.CassandraSchemaInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * This application is a service that runs over a Cassandra DB to expose its data.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        prepareApplication(context);
    }

    private static void prepareApplication(ApplicationContext context) {

        // initialize cassandra
        CassandraSchemaInit cassandraSchemaInit = context.getBean(CassandraSchemaInit.class);
        cassandraSchemaInit.init();

    }

}