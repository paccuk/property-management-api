package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRoleRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Repositories.Users.UserRoleRepositoryImpl;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration("userRoleRepositoryConfiguration")
public class UserRoleRepositoryConfig {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final TransactionManager transactionManager;

    public UserRoleRepositoryConfig(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, TransactionManager transactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.transactionManager = transactionManager;
    }

    @Bean
    public UserRoleRepository userRoleRepository() {
        return new UserRoleRepositoryImpl(jdbcTemplate, simpleJdbcInsert, transactionManager);
    }
}