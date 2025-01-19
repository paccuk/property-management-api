package org.sandw.propertymanagementapi.modules.Properties.API.Mappers.Properties;

import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.CreatePropertyRequest;
import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyResponse;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.*;

import java.util.UUID;

public class PropertyMapper {
    public static Property toDomain(CreatePropertyRequest propertyDto) { // TODO: Remove from dto created date, id
        return Property.createNew(
                new OwnerId(UUID.fromString(propertyDto.ownerId())),
                propertyDto.name(),
                Location.of(propertyDto.locationAddress(), propertyDto.locationCity(), propertyDto.locationPostalCode()),
                PropertyType.of(propertyDto.type()),
                PropertyStatus.of(propertyDto.status()),
                RentalPlacesLimit.of(propertyDto.rentalLimit())
        );
    }

    public static PropertyResponse toResponseDto(Property property) {
        return new PropertyResponse(
                property.getId().getValue().toString(),
                property.getOwnerId().getValue().toString(),
                property.getName(),
                property.getLocation().getAddress(),
                property.getLocation().getCity(),
                property.getLocation().getPostalCode(),
                property.getType().getValue(),
                property.getStatus().getValue(),
                property.getRentalLimit().getValue(),
                property.getCreatedDate().toString()
        );
    }
}
