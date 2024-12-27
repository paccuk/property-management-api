package org.sandw.propertymanagementapi.buildingblocks.Domain;

import java.time.Instant;
import java.util.UUID;

public class DomainEventBase implements DomainEvent {

    private final UUID id;
    private final Instant occurredOn;

    public DomainEventBase() {
        this.id = UUID.randomUUID();
        this.occurredOn = Instant.now();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }
}
