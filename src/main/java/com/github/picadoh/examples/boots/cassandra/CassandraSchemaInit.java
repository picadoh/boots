package com.github.picadoh.examples.boots.cassandra;

import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provides the means to initialize the Cassandra keyspace and the necessary tables.
 */
@Component
public class CassandraSchemaInit {

    private Session session;

    @Autowired
    public CassandraSchemaInit(Session session) {
        this.session = session;
    }

    public void init() {
        String createKS = String.format("CREATE KEYSPACE IF NOT EXISTS %s " +
                "WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': '2' }",
                CassandraConfig.KEYSPACE);
        session.execute(createKS);

        String createTbl = String.format("CREATE TABLE IF NOT EXISTS %s.%s(id text, name text, PRIMARY KEY(id));",
                CassandraConfig.KEYSPACE, CassandraConfig.TABLE_PERSON);
        session.execute(createTbl);
    }

}
