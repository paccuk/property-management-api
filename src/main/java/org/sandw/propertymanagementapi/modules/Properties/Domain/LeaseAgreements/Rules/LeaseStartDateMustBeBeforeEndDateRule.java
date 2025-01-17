package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Rules;

import org.sandw.propertymanagementapi.buildingblocks.Domain.BusinessRule;

import java.time.LocalDateTime;

public class LeaseStartDateMustBeBeforeEndDateRule implements BusinessRule {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public LeaseStartDateMustBeBeforeEndDateRule(LocalDateTime endDate, LocalDateTime startDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    @Override
    public boolean isBroken() {
        return this.startDate.isAfter(this.endDate);
    }

    @Override
    public String getMessage() {
        return "Lease start date must be before end date";
    }
}
