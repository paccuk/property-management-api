package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class AgreementMemberId extends TypedIdValueBase {
    protected AgreementMemberId(UUID value) {
        super(value);
    }
}
