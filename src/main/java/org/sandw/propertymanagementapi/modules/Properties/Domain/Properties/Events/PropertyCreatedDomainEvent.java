package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.OwnerId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

public class PropertyCreatedDomainEvent extends DomainEventBase {

    public final PropertyId propertyId;
    public final OwnerId ownerId;

    public PropertyCreatedDomainEvent(PropertyId propertyId, OwnerId ownerId) {
        this.propertyId = propertyId;
        this.ownerId = ownerId;
    }
}
