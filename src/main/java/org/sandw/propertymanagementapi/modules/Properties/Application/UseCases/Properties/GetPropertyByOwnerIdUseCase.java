package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties;

import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyResponse;
import org.sandw.propertymanagementapi.modules.Properties.API.Mappers.Properties.PropertyMapper;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.OwnerId;

import java.util.UUID;

public class GetPropertyByOwnerIdUseCase {
    private final PropertyRepository propertyRepository;

    public GetPropertyByOwnerIdUseCase(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public PropertyResponse execute(String id) {
        var ownerId = new OwnerId(UUID.fromString(id));
        var property = propertyRepository.findByOwnerId(ownerId).orElseThrow();
        return PropertyMapper.toResponseDto(property);
    }
}
