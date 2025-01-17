package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseAgreementId;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.MoneyValue;

public class MonthlyRentFeeUpdatedDomainEvent extends DomainEventBase {

    public final LeaseAgreementId leaseAgreementId;
    public final MoneyValue monthlyRentFee;

    public MonthlyRentFeeUpdatedDomainEvent(LeaseAgreementId leaseAgreementId, MoneyValue monthlyRentFee) {
        this.leaseAgreementId = leaseAgreementId;
        this.monthlyRentFee = monthlyRentFee;
    }
}
