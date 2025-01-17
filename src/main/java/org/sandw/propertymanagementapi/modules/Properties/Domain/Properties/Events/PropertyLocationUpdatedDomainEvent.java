package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.Location;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;

@Getter
public class PropertyLocationUpdatedDomainEvent extends DomainEventBase {

    private final PropertyId propertyId;
    private final Location location;

    public PropertyLocationUpdatedDomainEvent(PropertyId propertyId, Location location) {
        this.propertyId = propertyId;
        this.location = location;
    }
}
