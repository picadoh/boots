package com.github.picadoh.examples.boots.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.ReconnectionPolicy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertSame;
import static org.testng.AssertJUnit.assertNotNull;

public class CassandraConfigTest {

    private CassandraConfig victim;

    @BeforeClass
    public void setupScenario() {
        victim = new CassandraConfig();
        victim.setCassandraHost("localhost");
    }

    @Test
    public void shouldGetCluster() {

        Cluster cluster = mock(Cluster.class);
        Cluster.Builder builder = mock(Cluster.Builder.class);

        CassandraConfig spiedVictim = spy(victim);

        doReturn(builder).when(spiedVictim).clusterBuilder();
        doReturn(builder).when(builder).addContactPoint(anyString());
        doReturn(builder).when(builder).withReconnectionPolicy(any(ReconnectionPolicy.class));
        doReturn(cluster).when(builder).build();

        Cluster createdCluster = spiedVictim.cluster();

        assertNotNull(createdCluster);
        assertSame(createdCluster, cluster);
        verify(builder).addContactPoint("localhost");
    }

    @Test
    public void shouldGetSession() throws Exception {
        Cluster cluster = mock(Cluster.class);
        Session session = mock(Session.class);

        CassandraConfig spiedVictim = spy(victim);

        doReturn(session).when(cluster).newSession();
        doReturn(cluster).when(spiedVictim).cluster();

        Session createdSession = spiedVictim.session();

        assertNotNull(createdSession);
        assertSame(createdSession, session);
    }

}
