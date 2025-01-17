package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;

import java.time.LocalDateTime;

public class LeaseEndDateUpdatedDomainEvent extends DomainEventBase {

    public final LeaseAgreementId leaseAgreementId;
    public final LocalDateTime leaseEndDate;

    public LeaseEndDateUpdatedDomainEvent(LeaseAgreementId leaseAgreementId, LocalDateTime leaseEndDate) {
        this.leaseAgreementId = leaseAgreementId;
        this.leaseEndDate = leaseEndDate;
    }
}
