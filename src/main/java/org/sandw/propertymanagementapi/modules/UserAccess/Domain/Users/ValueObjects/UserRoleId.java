package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class UserRoleId extends TypedIdValueBase {
    protected UserRoleId(UUID value) {
        super(value);
    }
}
