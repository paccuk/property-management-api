package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.OwnerId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

@Getter
public class PropertyCreatedDomainEvent extends DomainEventBase {

    private final PropertyId propertyId;
    protected final OwnerId ownerId;

    public PropertyCreatedDomainEvent(PropertyId propertyId, OwnerId ownerId) {
        this.propertyId = propertyId;
        this.ownerId = ownerId;
    }
}
