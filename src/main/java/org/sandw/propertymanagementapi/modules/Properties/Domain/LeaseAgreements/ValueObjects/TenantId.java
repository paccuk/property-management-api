package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class TenantId extends TypedIdValueBase {
    public TenantId(UUID id) {
        super(id);
    }
}
