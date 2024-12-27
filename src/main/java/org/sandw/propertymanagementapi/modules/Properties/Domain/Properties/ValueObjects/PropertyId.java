package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class PropertyId extends TypedIdValueBase {

    public PropertyId(UUID value) {
        super(value);
    }
}
