package com.github.picadoh.examples.boots.dao;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.github.picadoh.examples.boots.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.github.picadoh.examples.boots.cassandra.CassandraConfig.*;

/**
 * Provides access to {@see Person} in the underlying Cassandra cluster.
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    private final Session session;

    @Autowired
    public PersonDaoImpl(Session session) {
        this.session = session;
    }

    public List<Person> findAll() {
        Statement select = QueryBuilder
                .select().all()
                .from(KEYSPACE, TABLE_PERSON);

        ResultSet resultSet = session.execute(select);

        List<Person> personList = newArrayList();
        for (Row row : resultSet) {
            Person foundPerson = new Person(
                    row.getString(TABLE_PERSON_ID),
                    row.getString(TABLE_PERSON_NAME)
            );
            personList.add(foundPerson);
        }

        return personList;
    }

    public void insert(Person person) {
        Statement insert = QueryBuilder
                .insertInto(TABLE_PERSON)
                .value(TABLE_PERSON_ID, person.getId())
                .value(TABLE_PERSON_NAME, person.getName());

        session.execute(insert);
    }

}
