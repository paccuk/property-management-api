package org.sandw.propertymanagementapi.modules.Properties.API.Mappers.Properties;

import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyIdDto;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

import java.util.UUID;

public class PropertyIdMapper {
    public static PropertyId toDomain(PropertyIdDto propertyIdDto) {
        return new PropertyId(UUID.fromString(propertyIdDto.propertyId()));
    }
}
