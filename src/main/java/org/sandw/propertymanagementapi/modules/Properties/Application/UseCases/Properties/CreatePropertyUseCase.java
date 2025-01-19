package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.CreatePropertyRequest;
import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyResponse;
import org.sandw.propertymanagementapi.modules.Properties.API.Mappers.Properties.PropertyMapper;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.PropertyCreatedDomainEvent;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;

public class CreatePropertyUseCase {
    private final PropertyRepository propertyRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public CreatePropertyUseCase(PropertyRepository propertyRepository, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.propertyRepository = propertyRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    public PropertyResponse execute(CreatePropertyRequest propertyRequest) {
        return transactionManager.doInTransactionWithResult(() -> {

            var property = PropertyMapper.toDomain(propertyRequest);
            var createdProperty = propertyRepository.save(property);

            eventPublisher.publish(new PropertyCreatedDomainEvent(createdProperty.getId(), createdProperty.getOwnerId()));

            return PropertyMapper.toResponseDto(createdProperty);
        });
    }

}
