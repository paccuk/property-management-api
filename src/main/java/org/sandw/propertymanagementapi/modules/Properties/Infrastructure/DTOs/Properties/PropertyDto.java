package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.DTOs.Properties;


import java.time.LocalDateTime;
import java.util.UUID;

public record PropertyDto(
        UUID id,
        UUID ownerId,
        String name,
        String locationAddress,
        String locationCity,
        String locationPostalCode,
        String type,
        String status,
        Integer rentalLimit,
        LocalDateTime createdDate
) {
}
