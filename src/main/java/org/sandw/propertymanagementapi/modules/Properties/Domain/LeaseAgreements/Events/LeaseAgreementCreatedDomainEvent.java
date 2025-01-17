package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LessorId;

public class LeaseAgreementCreatedDomainEvent extends DomainEventBase {

    public final LeaseAgreementId leaseAgreementId;
    public final LessorId lessorId;

    public LeaseAgreementCreatedDomainEvent(LeaseAgreementId leaseAgreementId, LessorId lessorId) {
        this.leaseAgreementId = leaseAgreementId;
        this.lessorId = lessorId;
    }
}
