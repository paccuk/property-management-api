package org.sandw.propertymanagementapi.buildingblocks.Domain;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    UUID id = null;
    Instant occurredOn = null;

    UUID getId();

    Instant getOccurredOn();
}
