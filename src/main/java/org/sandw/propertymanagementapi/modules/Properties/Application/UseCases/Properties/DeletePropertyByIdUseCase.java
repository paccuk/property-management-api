package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.PropertyDeletedDomainEvent;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;

import java.util.UUID;

public class DeletePropertyByIdUseCase {
    private final PropertyRepository propertyRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public DeletePropertyByIdUseCase(PropertyRepository propertyRepository, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.propertyRepository = propertyRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    public void execute(String id) {
        transactionManager.doInTransaction(() -> {
            var propertyId = new PropertyId(UUID.fromString(id));

            propertyRepository.findById(propertyId).orElseThrow();
            propertyRepository.deleteById(propertyId);

            eventPublisher.publish(new PropertyDeletedDomainEvent(propertyId));
        });
    }
}
