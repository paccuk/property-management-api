package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Repositories.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRoleRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserRoleDto;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.RowMappers.Users.UserRoleRowMapper;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRoleRepositoryImpl implements UserRoleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final TransactionManager transactionManager;

    public UserRoleRepositoryImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, TransactionManager transactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.transactionManager = transactionManager;
    }


    @Override
    public void assignRolesByUserId(List<UUID> userRoleIds, UUID userId) {
        transactionManager.doInTransaction(() -> {
            String query = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
            List<Object[]> params = userRoleIds.stream().map(userRoleId -> new Object[]{userId, userRoleId}).toList();
            jdbcTemplate.batchUpdate(query, params);
        });
    }

    @Override
    public void assignRoleByUserId(UUID userRoleId, UUID userId) {
        transactionManager.doInTransaction(() -> {
            String query = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";
            jdbcTemplate.update(query, userId, userRoleId);
        });
    }

    @Override
    public void removeRolesByUserId(List<UUID> userRoleIds, UUID userId) {
        transactionManager.doInTransaction(() -> {
            String query = "DELETE FROM user_roles WHERE user_id = ? AND role_id = ?";
            List<Object[]> params = userRoleIds.stream().map(userRoleId -> new Object[]{userId, userRoleId}).toList();
            jdbcTemplate.batchUpdate(query, params);
        });
    }

    @Override
    public void removeRoleByUserId(UUID userRoleId, UUID userId) {
        transactionManager.doInTransaction(() -> {
            String query = "DELETE FROM user_roles WHERE user_id = ? AND role_id = ?";
            jdbcTemplate.update(query, userId, userId);
        });
    }

    @Override
    public List<UserRoleDto> findRolesByNames(List<String> names) {
        return transactionManager.doInTransactionWithResult(() -> {
            String query = "SELECT * FROM roles WHERE name IN ?";
            return jdbcTemplate.query(query, new UserRoleRowMapper(), names);
        });
    }

    @Override
    public Optional<UserRoleDto> findRoleByName(String name) {
        return transactionManager.doInTransactionWithResult(() -> {
            String query = "SELECT * FROM roles WHERE name = ?";
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(query, new UserRoleRowMapper(), name)
            );
        });
    }

    @Override
    public List<UserRoleDto> findRolesByUserId(UserId userId) {
        return transactionManager.doInTransactionWithResult(() -> {
            String query = """
                        SELECT r.*
                        FROM roles r
                        INNER JOIN user_roles ur ON r.role_id = ur.role_id
                        WHERE ur.user_id = ?
                    """;
            return jdbcTemplate.query(query, new UserRoleRowMapper(), userId);
        });
    }
}
