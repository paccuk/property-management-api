package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.RowMappers.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserRowMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserDto(
                UUID.fromString(rs.getString("user_id")),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone"),
                LocalDateTime.of(rs.getDate("created_date").toLocalDate(), rs.getTime("created_date").toLocalTime())
        );
    }
}
