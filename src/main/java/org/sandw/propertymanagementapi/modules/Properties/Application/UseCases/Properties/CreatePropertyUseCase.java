package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;


public class CreatePropertyUseCase {
    private final PropertyRepository propertyRepository;

    public CreatePropertyUseCase(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property execute(Property property) {
        propertyRepository.save(property); // TODO: Dorobyty
        return property;
    }

}
