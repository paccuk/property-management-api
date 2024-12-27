package org.sandw.propertymanagementapi.buildingblocks.Domain;

import lombok.Getter;

@Getter
public class BusinessRuleValidationException extends RuntimeException {
    private final BusinessRule brokenRule;
    private final String details;

    public BusinessRuleValidationException(BusinessRule brokenRule) {
        super(brokenRule.getMessage());
        this.brokenRule = brokenRule;
        this.details = brokenRule.getMessage();
    }

    @Override
    public String toString() {
        return String.format("%s: %s", brokenRule.getClass().getName(), brokenRule.getMessage());
    }
}
