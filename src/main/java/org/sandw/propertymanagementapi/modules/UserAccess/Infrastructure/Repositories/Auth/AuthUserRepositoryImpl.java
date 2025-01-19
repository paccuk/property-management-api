package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Repositories.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUser;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Mappers.Auth.AuthUserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.RowMappers.Auth.AuthUserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthUserRepositoryImpl implements AuthUserRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public AuthUserRepositoryImpl(SimpleJdbcInsert simpleJdbcInsert, JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = simpleJdbcInsert;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public AuthUser save(AuthUser authUser) {
        var authUserDto = AuthUserMapper.toDto(authUser);

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", authUserDto.id());
        params.put("email", authUserDto.email());
        params.put("password", authUserDto.password());
        params.put("role", authUserDto.role());

        simpleJdbcInsert.withTableName("auth_user").execute(params);

        return authUser;
    }

    @Override
    public Optional<AuthUser> findByEmail(String email) {
        String query = "SELECT * FROM auth_user WHERE email = ?";

        var authUserDto = Optional.ofNullable(
                jdbcTemplate.queryForObject(query, new AuthUserRowMapper(), email)
        );

        return authUserDto.map(AuthUserMapper::toDomain);
    }

//    @Override
//    public Optional<AuthUser> findByEmail(String email) {
//        String query = "SELECT * FROM auth_user WHERE email = ?";
//
//        try {
//            var authUserDto = Optional.ofNullable(
//                    jdbcTemplate.queryForObject(query, new AuthUserRowMapper(), email)
//            );
//            return authUserDto.map(AuthUserMapper::toDomain);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }

    @Override
    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }
}
