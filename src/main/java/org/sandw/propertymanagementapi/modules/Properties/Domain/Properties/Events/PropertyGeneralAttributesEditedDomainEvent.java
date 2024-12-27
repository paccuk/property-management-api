package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.RentalPlacesLimit;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.Location;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyType;

public class PropertyGeneralAttributesEditedDomainEvent extends DomainEventBase {

    public final PropertyId propertyId;

    public String name;
    public Location location;
    public PropertyType propertyType;
    public RentalPlacesLimit availibleToRentPlaces;

    public PropertyGeneralAttributesEditedDomainEvent(PropertyId propertyId, String name, Location location, PropertyType propertyType, RentalPlacesLimit availibleToRentPlaces) {
        this.propertyId = propertyId;
        this.name = name;
        this.location = location;
        this.propertyType = propertyType;
        this.availibleToRentPlaces = availibleToRentPlaces;
    }
}
