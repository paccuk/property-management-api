package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyType;

@Getter
public class PropertyTypeUpdatedDomainEvent extends DomainEventBase {

    private final PropertyId propertyId;
    private final PropertyType propertyType;

    public PropertyTypeUpdatedDomainEvent(PropertyId propertyId, PropertyType propertyType) {
        this.propertyId = propertyId;
        this.propertyType = propertyType;
    }
}
