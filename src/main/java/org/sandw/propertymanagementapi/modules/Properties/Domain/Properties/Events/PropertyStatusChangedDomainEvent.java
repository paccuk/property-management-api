package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

public class PropertyStatusChangedDomainEvent extends DomainEventBase {

    public final PropertyId propertyId;

    public PropertyStatusChangedDomainEvent(PropertyId propertyId) {
        this.propertyId = propertyId;
    }
}
