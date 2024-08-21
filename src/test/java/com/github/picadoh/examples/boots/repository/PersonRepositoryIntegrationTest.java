package com.github.picadoh.examples.boots.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.picadoh.examples.boots.domain.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.CassandraContainer;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("integration-test")
public class PersonRepositoryIntegrationTest {
    private final CassandraContainer cassandra = new CassandraContainer("cassandra")
            .withInitScript("test-init.cql");

    @BeforeEach
    public void setup() {
        cassandra.start();
    }

    @AfterEach
    public void cleanup() {
        cassandra.stop();
    }

    @Test
    public void shouldFindAll() {
        var session = CqlSession
                .builder()
                .addContactPoint(cassandra.getContactPoint())
                .withLocalDatacenter(cassandra.getLocalDatacenter())
                .build();

        var personRepository = new PersonRepository(session, "testks");

        var people = personRepository.findAll();

        assertThat(people).containsExactlyInAnyOrder(
                new Person("XYZ123", "John"),
                new Person("ZYX567", "Anna")
        );
    }
}
