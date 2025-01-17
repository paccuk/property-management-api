package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.RowMappers.LeaseAgreements;

import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.DTOs.LeaseAgreements.LeaseAgreementDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class LeaseAgreementRowMapper implements RowMapper<LeaseAgreementDto> {
    @Override
    public LeaseAgreementDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LeaseAgreementDto(
                UUID.fromString(rs.getString("lease_agreement_id")),
                UUID.fromString(rs.getString("property_id")),
                UUID.fromString(rs.getString("lessor_id")),
                UUID.fromString(rs.getString("tenant_id")),
                rs.getDouble("monthly_rent_fee"),
                rs.getString("currency"),
                rs.getString("status"),
                LocalDateTime.of(rs.getDate("created_date").toLocalDate(), rs.getTime("created_date").toLocalTime()),
                LocalDateTime.of(rs.getDate("modified_date").toLocalDate(), rs.getTime("modified_date").toLocalTime()),
                LocalDateTime.of(rs.getDate("modified_date").toLocalDate(), rs.getTime("modified_date").toLocalTime()),
                LocalDateTime.of(rs.getDate("lease_end_date").toLocalDate(), rs.getTime("lease_end_date").toLocalTime())
        );
    }
}
