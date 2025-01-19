package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Users;


import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.UserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Repositories.Users.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration("userRepositoryConfiguration")
public class UserRepositoryConfig {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public UserRepositoryConfig(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(jdbcTemplate, simpleJdbcInsert);
    }
}
