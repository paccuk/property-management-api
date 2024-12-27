package org.sandw.propertymanagementapi.buildingblocks.Domain;

public interface BusinessRule {
    boolean isBroken();

    String getMessage();

}
