package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects.AuthUserId;

public class UserRegisteredDomainEvent extends DomainEventBase {
    public final AuthUserId userId;

    public UserRegisteredDomainEvent(AuthUserId authUserId) {
        this.userId = authUserId;
    }
}
