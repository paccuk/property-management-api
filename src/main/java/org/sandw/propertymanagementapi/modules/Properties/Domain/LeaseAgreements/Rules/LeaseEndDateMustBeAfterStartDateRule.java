package org.sandw.propertymanagementapi.modules.Properties.Domain.LeaseAgreements.Rules;

import org.sandw.propertymanagementapi.buildingblocks.Domain.BusinessRule;

import java.time.LocalDateTime;

public class LeaseEndDateMustBeAfterStartDateRule implements BusinessRule {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;


    public LeaseEndDateMustBeAfterStartDateRule(LocalDateTime leaseStartDate, LocalDateTime leaseEndDate) {
        this.startDate = leaseStartDate;
        this.endDate = leaseEndDate;
    }

    @Override
    public boolean isBroken() {
        return this.endDate.isBefore(this.startDate);
    }

    @Override
    public String getMessage() {
        return "Lease end date must be after start date";
    }
}
