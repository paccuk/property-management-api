package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyStatus;

@Getter
public class PropertyStatusChangedDomainEvent extends DomainEventBase {

    public final PropertyId propertyId;
    public final PropertyStatus status;

    public PropertyStatusChangedDomainEvent(PropertyId propertyId, PropertyStatus status) {
        this.propertyId = propertyId;
        this.status = status;
    }
}
