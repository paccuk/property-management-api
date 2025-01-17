package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;

public class LeaseAgreementTerminatedDomainEvent extends DomainEventBase {

    public final LeaseAgreementId leaseAgreementId;

    public LeaseAgreementTerminatedDomainEvent(LeaseAgreementId leaseAgreementId) {
        this.leaseAgreementId = leaseAgreementId;
    }
}
