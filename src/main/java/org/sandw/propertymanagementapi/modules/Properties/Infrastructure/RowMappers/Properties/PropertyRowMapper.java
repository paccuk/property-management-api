package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.RowMappers.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.DTOs.Properties.PropertyDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class PropertyRowMapper implements RowMapper<PropertyDto> {
    @Override
    public PropertyDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PropertyDto(
                UUID.fromString(rs.getString("property_id")),
                UUID.fromString(rs.getString("owner_id")),
                rs.getString("name"),
                rs.getString("location_address"),
                rs.getString("location_city"),
                rs.getString("location_postal_code"),
                rs.getString("type"),
                rs.getString("status"),
                rs.getInt("rental_limit"),
                LocalDateTime.of(rs.getDate("created_date").toLocalDate(), rs.getTime("created_date").toLocalTime())
        );
    }
}
