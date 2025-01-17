package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.Auth.Events;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;

public class UserRegisteredDomainEvent extends DomainEventBase {
    public final UserId userId;

    public UserRegisteredDomainEvent(UserId userId) {
        this.userId = userId;
    }
}
