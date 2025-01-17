package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class LeaseAgreementId extends TypedIdValueBase {

    public LeaseAgreementId(UUID value) {
        super(value);
    }
}
