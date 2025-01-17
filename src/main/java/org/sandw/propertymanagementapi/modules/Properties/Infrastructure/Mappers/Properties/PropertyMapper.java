package org.sandw.propertymanagementapi.modules.Properties.Infrastructure.Mappers.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.*;
import org.sandw.propertymanagementapi.modules.Properties.Infrastructure.DTOs.Properties.PropertyDto;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;

public class PropertyMapper {
    public static Property toDomain(PropertyDto propertyDto) {
        return Property.createNew(
                new PropertyId(propertyDto.id()),
                new OwnerId(propertyDto.ownerId()),
                propertyDto.createdDate(),
                propertyDto.name(),
                Location.of(propertyDto.locationAddress(), propertyDto.locationCity(), propertyDto.locationPostalCode()),
                PropertyType.of(propertyDto.type()),
                PropertyStatus.of(propertyDto.status()),
                RentalPlacesLimit.of(propertyDto.rentalLimit())
        );
    }

    public static PropertyDto toDto(Property property) {
        return new PropertyDto(
                property.getId().getValue(),
                property.getOwnerId().getValue(),
                property.getName(),
                property.getLocation().getAddress(),
                property.getLocation().getCity(),
                property.getLocation().getPostalCode(),
                property.getType().getValue(),
                property.getStatus().getValue(),
                property.getRentalLimit().getValue(),
                property.getCreatedDate()
        );
    }
}
