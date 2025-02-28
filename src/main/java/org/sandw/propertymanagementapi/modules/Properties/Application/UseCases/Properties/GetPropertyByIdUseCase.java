package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties;

import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyResponse;
import org.sandw.propertymanagementapi.modules.Properties.API.Mappers.Properties.PropertyMapper;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

import java.util.UUID;

public class GetPropertyByIdUseCase {
    private final PropertyRepository propertyRepository;

    public GetPropertyByIdUseCase(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public PropertyResponse execute(String id) {
        var propertyId = new PropertyId(UUID.fromString(id));
        var property = propertyRepository.findById(propertyId).orElseThrow();
        return PropertyMapper.toResponseDto(property);
    }
}

