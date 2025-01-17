package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;


public class GetPropertyByIdUseCase {
    private final PropertyRepository propertyRepository;

    public GetPropertyByIdUseCase(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property execute(PropertyId propertyId) {
        return propertyRepository.findById(propertyId).orElseThrow(); // TODO: Handle null exceptions
//        throw new RuntimeException("Get property failed", ex);
    }
}

