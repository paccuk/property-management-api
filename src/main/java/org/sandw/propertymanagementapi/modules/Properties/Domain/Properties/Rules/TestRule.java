package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Rules;

import org.sandw.propertymanagementapi.buildingblocks.Domain.BusinessRule;

public class TestRule implements BusinessRule {
    public TestRule() {

    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public String getMessage() {
        return "";
    }
}
