package org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.ValueObjects.UserId;

@Getter
public class UserCreatedDomainEvent extends DomainEventBase {
    private final UserId userId;

    public UserCreatedDomainEvent(UserId userId) {
        this.userId = userId;
    }
}
