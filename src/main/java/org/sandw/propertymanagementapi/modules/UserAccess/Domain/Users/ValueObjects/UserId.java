package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class UserId extends TypedIdValueBase {
    public UserId(UUID value) {
        super(value);
    }
}
