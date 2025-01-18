package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.RowMappers.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Auth.AuthUserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AuthUserRowMapper implements RowMapper<AuthUserDto> {
    @Override
    public AuthUserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AuthUserDto(
                UUID.fromString(rs.getString("user_id")),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
        );
    }
}
