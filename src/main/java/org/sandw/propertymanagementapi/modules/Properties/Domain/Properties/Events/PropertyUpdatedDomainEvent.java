package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

@Getter
public class PropertyUpdatedDomainEvent extends DomainEventBase {
    private final PropertyId propertyId;

    public PropertyUpdatedDomainEvent(PropertyId propertyId) {
        this.propertyId = propertyId;
    }
}
