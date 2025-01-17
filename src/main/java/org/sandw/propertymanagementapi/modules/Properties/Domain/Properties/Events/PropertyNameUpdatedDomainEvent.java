package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

@Getter
public class PropertyNameUpdatedDomainEvent extends DomainEventBase {

    private final PropertyId propertyId;
    private final String name;

    public PropertyNameUpdatedDomainEvent(PropertyId propertyId, String name) {
        this.propertyId = propertyId;
        this.name = name;
    }
}
