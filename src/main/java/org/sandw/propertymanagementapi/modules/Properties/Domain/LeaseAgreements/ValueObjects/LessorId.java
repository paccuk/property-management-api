package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class LessorId extends TypedIdValueBase {
    public LessorId(UUID value) {
        super(value);
    }
}
