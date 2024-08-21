package com.github.picadoh.examples.boots.config;

import com.datastax.oss.driver.api.core.CqlSession;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class LocalSchemaSetup {
    private final CqlSession session;
    private final String keyspace;

    public LocalSchemaSetup(
            CqlSession session,
            @Value("${cassandra.keyspace}") String keyspace
    ) {
        this.session = session;
        this.keyspace = keyspace;
    }

    @PostConstruct
    public void setup() {
        session.execute("DROP KEYSPACE IF EXISTS " + keyspace + ";");
        session.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspace + " WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};");
        session.execute("CREATE TABLE " + keyspace + ".people(id text primary key, name text);");
        session.execute("INSERT INTO " + keyspace + ".people (id, name) values ('XYZ123', 'John');");
        session.execute("INSERT INTO " + keyspace + ".people (id, name) values ('ZYX567', 'Anna');");
    }
}
