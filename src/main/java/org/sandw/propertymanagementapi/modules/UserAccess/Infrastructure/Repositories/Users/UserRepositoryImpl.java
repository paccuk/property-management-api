package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Repositories.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRoleRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserRole;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserRoleDto;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Mappers.Users.UserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.RowMappers.Users.UserRoleRowMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.RowMappers.Users.UserRowMapper;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final UserRoleRepository userRoleRepository;

    private final TransactionManager transactionManager;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert, UserRoleRepository userRoleRepository, TransactionManager transactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.userRoleRepository = userRoleRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public void save(User user) {
        transactionManager.doInTransaction(() -> {
            var userDto = UserMapper.toDto(user);

            Map<String, Object> userParams = new HashMap<>();
            userParams.put("user_id", userDto.id());
            userParams.put("first_name", userDto.firstName());
            userParams.put("last_name", userDto.lastName());
            userParams.put("password_hashed", userDto.password());
            userParams.put("email", userDto.email());
            userParams.put("phone", userDto.phone());
            userParams.put("created_date", userDto.createdDate());

            simpleJdbcInsert.withTableName("user").execute(userParams);

            var roleIds = userRoleRepository.findRolesByNames(userDto.roles())
                    .stream().map(UserRoleDto::id).toList();
            userRoleRepository.assignRolesByUserId(roleIds, userDto.id());
        });
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return transactionManager.doInTransactionWithResult(() -> {
            String query = "SELECT * FROM user WHERE user_id = ?";
            var userDto = Optional.ofNullable(
                    jdbcTemplate.queryForObject(query, new UserRowMapper(), userId.getValue())
            );

            return userDto.map(dto -> {
                var userRoleDtos = userRoleRepository.findRolesByUserId(userId);
                return UserMapper.toDomain(dto, userRoleDtos);
            });
        });
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return transactionManager.doInTransactionWithResult(() -> {
            String query = "SELECT * FROM user WHERE email = ?";
            var userDto = Optional.ofNullable(
                    jdbcTemplate.queryForObject(query, new UserRowMapper(), email)
            );

            return userDto.map(dto -> {
                var userRoles = userRoleRepository.findRolesByUserId(new UserId(dto.id()));
                return UserMapper.toDomain(dto, userRoles);
            });
        });
    }


}
