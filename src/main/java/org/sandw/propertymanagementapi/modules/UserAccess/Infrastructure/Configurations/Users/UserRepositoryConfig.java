package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Users;


import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRoleRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Repositories.Users.UserRepositoryImpl;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration("userRepositoryConfiguration")
public class UserRepositoryConfig {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final TransactionManager transactionManager;
    private final UserRoleRepository userRoleRepository;

    public UserRepositoryConfig(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, TransactionManager transactionManager, UserRoleRepository userRoleRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.transactionManager = transactionManager;
        this.userRoleRepository = userRoleRepository;
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(jdbcTemplate, simpleJdbcInsert, userRoleRepository, transactionManager);
    }
}
