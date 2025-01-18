package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects;

import org.sandw.propertymanagementapi.buildingblocks.Domain.TypedIdValueBase;

import java.util.UUID;

public class AuthUserId extends TypedIdValueBase {
    public AuthUserId(UUID value) {
        super(value);
    }
}
