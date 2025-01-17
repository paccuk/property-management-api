package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class OwnerId extends TypedIdValueBase {
    public OwnerId(UUID value) {
        super(value);
    }

    public static OwnerId of(UUID value) {
        return new OwnerId(value);
    }

}
