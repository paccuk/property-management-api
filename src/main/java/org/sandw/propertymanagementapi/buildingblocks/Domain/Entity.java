package org.sandw.propertymanagementapi.buildingblocks.Domain;

public class Entity {

    protected void checkRule(BusinessRule rule) {
        if (rule.isBroken()) {
            throw new BusinessRuleValidationException(rule);
        }
    }
}
