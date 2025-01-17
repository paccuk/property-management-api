package org.sandw.propertymanagementapi.modules.Properties.API.Mappers.Properties;

import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyDto;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class PropertyMapper {
        public static Property toDomain(PropertyDto propertyDto) { // TODO: Remove from dto created date, id
        return Property.createNew(
                new PropertyId(UUID.fromString(propertyDto.id())),
                new OwnerId(UUID.fromString(propertyDto.ownerId())),
                LocalDateTime.parse(propertyDto.createdDate()), // TODO: Review approach of parsing.
                propertyDto.name(),
                Location.of(propertyDto.locationAddress(), propertyDto.locationCity(), propertyDto.locationPostalCode()),
                PropertyType.of(propertyDto.type()),
                PropertyStatus.of(propertyDto.status()),
                RentalPlacesLimit.of(propertyDto.rentalLimit())
        );
    }
    public static PropertyDto toDto(Property property) {
        return new PropertyDto(
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
