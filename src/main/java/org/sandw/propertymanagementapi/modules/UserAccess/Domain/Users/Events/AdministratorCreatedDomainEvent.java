package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.Events;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEventBase;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;

@Getter
public class AdministratorCreatedDomainEvent extends DomainEventBase {

    private final UserId userId;

    public AdministratorCreatedDomainEvent(UserId userId) {
        this.userId = userId;
    }
}
