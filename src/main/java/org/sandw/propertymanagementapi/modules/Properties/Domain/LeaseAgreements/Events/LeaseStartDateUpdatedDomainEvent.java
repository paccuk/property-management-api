package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;

import java.time.LocalDateTime;

public class LeaseStartDateUpdatedDomainEvent extends DomainEventBase {

    public final LeaseAgreementId leaseAgreementId;
    public final LocalDateTime leaseStartDate;

    public LeaseStartDateUpdatedDomainEvent(LeaseAgreementId leaseAgreementId, LocalDateTime leaseStartDate) {
        this.leaseAgreementId = leaseAgreementId;
        this.leaseStartDate = leaseStartDate;
    }
}
