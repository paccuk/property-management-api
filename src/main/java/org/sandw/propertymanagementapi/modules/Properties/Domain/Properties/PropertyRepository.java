package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

import java.util.Optional;

public interface PropertyRepository {
    void save(Property property);

    Optional<Property> findById(PropertyId propertyId);
}
