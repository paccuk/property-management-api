package org.sandw.propertymanagementapi.buildingblocks.Domain;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    UUID getId();

    Instant getOccurredOn();
}
