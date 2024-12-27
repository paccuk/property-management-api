package org.sandw.propertymanagementapi.modules.Properties.Domain.Owners;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class OwnerId extends TypedIdValueBase {
    protected OwnerId(UUID value) {
        super(value);
    }
}
