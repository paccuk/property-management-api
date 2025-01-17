package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.RentalPlacesLimit;

@Getter
public class PropertyRentalLimitUpdatedDomainEvent extends DomainEventBase {

    private PropertyId propertyId;
    private RentalPlacesLimit rentalLimit;

    public PropertyRentalLimitUpdatedDomainEvent(PropertyId propertyId, RentalPlacesLimit rentalLimit) {
        this.propertyId = propertyId;
        this.rentalLimit = rentalLimit;
    }
}
