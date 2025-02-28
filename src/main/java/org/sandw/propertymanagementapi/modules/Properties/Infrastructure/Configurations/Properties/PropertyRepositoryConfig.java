package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Configurations.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Repositories.Properties.PropertyRepositoryImpl;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration("propertyRepositoryConfiguration")
public class PropertyRepositoryConfig {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public PropertyRepositoryConfig(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Bean
    public PropertyRepository propertyRepository() {
        return new PropertyRepositoryImpl(jdbcTemplate, simpleJdbcInsert);
    }
}
