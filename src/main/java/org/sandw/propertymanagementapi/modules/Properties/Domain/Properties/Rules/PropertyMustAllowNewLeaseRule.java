package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Rules;

import org.sandw.propertymanagementapi.buildingblocks.Domain.BusinessRule;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyStatus;

public class PropertyMustAllowNewLeaseRule implements BusinessRule {
    public PropertyMustAllowNewLeaseRule(PropertyStatus status) {
    }
}
