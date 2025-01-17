package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.DTOs.LeaseAgreements;


import java.time.LocalDateTime;
import java.util.UUID;

public record LeaseAgreementDto(
        UUID id,
        UUID propertyId,
        UUID lessorId,
        UUID tenantId,
        Double monthlyRentFee,
        String currency,
        String status,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        LocalDateTime leaseStartDate,
        LocalDateTime leaseEndDate
) {
}
