package org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyResponse;
import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.UpdatePropertyRequest;
import org.sandw.propertymanagementapi.modules.Properties.API.Mappers.Properties.PropertyMapper;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.PropertyUpdatedDomainEvent;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Property;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.PropertyRepository;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.*;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;

import java.util.UUID;

public class UpdatePropertyByIdUseCase {
    private final PropertyRepository propertyRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public UpdatePropertyByIdUseCase(PropertyRepository propertyRepository, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.propertyRepository = propertyRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    public PropertyResponse execute(String id, UpdatePropertyRequest propertyUpdateRequest) {
        return transactionManager.doInTransactionWithResult(() -> {
            var propertyId = new PropertyId(UUID.fromString(id));

            var existingProperty = propertyRepository.findById(propertyId).orElseThrow();
            var mergedProperty = mergeProperties(existingProperty, propertyUpdateRequest);
            var updatedProperty = propertyRepository.update(mergedProperty).orElseThrow();

            eventPublisher.publish(new PropertyUpdatedDomainEvent(propertyId));

            return PropertyMapper.toResponseDto(updatedProperty);
        });
    }

    private Property mergeProperties(Property existingProperty, UpdatePropertyRequest propertyUpdateRequest) {
        if (propertyUpdateRequest.getLocationAddress().isPresent() && propertyUpdateRequest.getLocationCity().isPresent() && propertyUpdateRequest.getLocationPostalCode().isPresent()) {
            var location = Location.of(propertyUpdateRequest.getLocationAddress().get(), propertyUpdateRequest.getLocationCity().get(), propertyUpdateRequest.getLocationPostalCode().get());
            existingProperty.updateLocation(location);
        }

        propertyUpdateRequest.getName().ifPresent(existingProperty::updateName);
        propertyUpdateRequest.getType().ifPresent(type -> existingProperty.updateType(PropertyType.of(type)));
        propertyUpdateRequest.getRentalLimit().ifPresent(rentalLimit -> existingProperty.updateRentalLimit(RentalPlacesLimit.of(rentalLimit)));
        propertyUpdateRequest.getStatus().ifPresent(status -> existingProperty.updateStatus(PropertyStatus.of(status)));

        return existingProperty;
    }
}
