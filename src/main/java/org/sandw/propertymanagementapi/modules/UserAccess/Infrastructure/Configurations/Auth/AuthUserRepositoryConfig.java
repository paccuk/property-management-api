package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Repositories.Auth.AuthUserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration("authUserRepositoryConfiguration")
public class AuthUserRepositoryConfig {
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public AuthUserRepositoryConfig(SimpleJdbcInsert simpleJdbcInsert, JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public AuthUserRepository authUserRepository() {
        return new AuthUserRepositoryImpl(simpleJdbcInsert, jdbcTemplate);
    }
}
