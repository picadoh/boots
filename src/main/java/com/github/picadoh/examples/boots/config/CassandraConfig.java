package com.github.picadoh.examples.boots.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.InetSocketAddress;

@Configuration
@Profile("local")
public class CassandraConfig {
    @Bean
    public CqlSession cqlSession(
            @Value("${cassandra.hostname}") String hostname,
            @Value("${cassandra.port}") Integer port,
            @Value("${cassandra.keyspace}") String keyspace
    ) {
        return new CqlSessionBuilder()
                .withLocalDatacenter("datacenter1")
                .addContactPoint(new InetSocketAddress(hostname, port))
                .withKeyspace(keyspace)
                .build();
    }
}
