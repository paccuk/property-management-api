package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.RowMappers.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserRoleDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRoleRowMapper implements RowMapper<UserRoleDto> {
    @Override
    public UserRoleDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserRoleDto(
                UUID.fromString(rs.getString("role_id")),
                rs.getString("name")
        );
    }
}
