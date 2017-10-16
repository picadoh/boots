package com.github.picadoh.examples.boots.util;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static org.testng.Assert.assertEquals;

/**
 * Loads cassandra embedded and provides tear up and tear down behaviours.
 *
 * @author picadoh
 */
public class AbstractCassandraTest {

    protected CassandraEmbedded cassandra = new CassandraEmbedded(new ClassPathCQLDataSet("db_setup.cql", "sampleks"), null, 60000L, 10000);

    @BeforeClass
    public void tearUp() throws Exception {
        cassandra.start();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        cassandra.stop();
    }

    protected Row fetchPersonRowById(String id) {
        //then
        Statement select = QueryBuilder
                .select().all()
                .from("person")
                .where(eq("id", id));

        ResultSet resultSet = cassandra.session.execute(select);

        List<Row> rows = resultSet.all();
        assertEquals(rows.size(), 1);

        return rows.get(0);
    }

}
