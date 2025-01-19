package org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties;

public record CreatePropertyRequest(
        String ownerId,
        String name,
        String locationAddress,
        String locationCity,
        String locationPostalCode,
        String type,
        String status,
        Integer rentalLimit
) {
}
