package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Repositories.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Mappers.Users.UserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.RowMappers.Users.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }


    @Override
    public User save(User user) {
            var userDto = UserMapper.toDto(user);

            Map<String, Object> userParams = new HashMap<>();
            userParams.put("user_id", userDto.id());
            userParams.put("first_name", userDto.firstName());
            userParams.put("last_name", userDto.lastName());
            userParams.put("email", userDto.email());
            userParams.put("phone", userDto.phone());
            userParams.put("created_date", userDto.createdDate());

            simpleJdbcInsert.withTableName("user").execute(userParams);

            return user;
    }

    @Override
    public Optional<User> findById(UserId userId) {
            String query = "SELECT * FROM user WHERE user_id = ?";
            var userDto = Optional.ofNullable(
                    jdbcTemplate.queryForObject(query, new UserRowMapper(), userId.getValue())
            );

            return userDto.map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
            String query = "SELECT * FROM user WHERE email = ?";
            var userDto = Optional.ofNullable(
                    jdbcTemplate.queryForObject(query, new UserRowMapper(), email)
            );

            return userDto.map(UserMapper::toDomain);
    }
}
