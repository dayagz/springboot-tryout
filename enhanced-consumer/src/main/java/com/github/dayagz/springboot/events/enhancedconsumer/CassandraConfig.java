package com.github.dayagz.springboot.events.enhancedconsumer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

//@Configuration
//@EnableCassandraRepositories(basePackages = "org.baeldung.spring.data.cassandra.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return null;
    }
}
