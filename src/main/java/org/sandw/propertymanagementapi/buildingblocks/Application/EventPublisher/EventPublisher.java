package org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher;

import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEvent;

import java.util.List;

public interface EventPublisher {
    void publish(DomainEvent event);
}
