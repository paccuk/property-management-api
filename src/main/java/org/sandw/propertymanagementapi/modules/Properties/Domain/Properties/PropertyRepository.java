package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties;

import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.OwnerId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

import java.util.Optional;

public interface PropertyRepository {
    Property save(Property property);

    Optional<Property> update(Property mergedProperty);

    void deleteById(PropertyId propertyId);

    Optional<Property> findById(PropertyId propertyId);

    Optional<Property> findByOwnerId(OwnerId ownerId);
}
