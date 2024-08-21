package com.github.picadoh.examples.boots.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.github.picadoh.examples.boots.domain.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRepository {
    private static final String TABLE_NAME = "people";

    private final CqlSession session;
    private final String keyspace;

    public PersonRepository(
            CqlSession session,
            @Value("${cassandra.keyspace}") String keyspace
    ) {
        this.session = session;
        this.keyspace = keyspace;
    }

    public List<Person> findAll() {
        var statement = QueryBuilder
                .selectFrom(keyspace, TABLE_NAME)
                .all()
                .build();

        var resultSet = session.execute(statement);

        var result = new ArrayList<Person>();
        for (var row : resultSet) {
            result.add(new Person(
                    row.getString("id"),
                    row.getString("name")
            ));
        }
        return result;
    }
}
