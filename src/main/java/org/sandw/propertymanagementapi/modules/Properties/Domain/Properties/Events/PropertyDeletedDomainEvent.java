package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

@Getter
public class PropertyDeletedDomainEvent extends DomainEventBase {
    private final PropertyId propertyId;

    public PropertyDeletedDomainEvent(PropertyId propertyId) {
        this.propertyId = propertyId;
    }
}
