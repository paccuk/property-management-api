package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Rules;

import org.sandw.propertymanagementapi.buildingblocks.Domain.BusinessRule;
import org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.ValueObjects.LeaseStatus;

public class LeaseAgreementCannotBeTerminatedTwice implements BusinessRule {

    private final LeaseStatus status;

    public LeaseAgreementCannotBeTerminatedTwice(LeaseStatus status) {
        this.status = status;
    }

    @Override
    public boolean isBroken() {
        return this.status.equals(LeaseStatus.Terminated);
    }

    @Override
    public String getMessage() {
        return "Lease agreement cannot be terminated twice";
    }
}
