package org.sandw.propertymanagementapi.modules.Properties.API.Controllers.Properties;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyDto;
import org.sandw.propertymanagementapi.modules.Properties.API.Mappers.Properties.PropertyMapper;
import org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties.CreatePropertyUseCase;
import org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties.GetPropertyByIdUseCase;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.Events.PropertyCreatedDomainEvent;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyId;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private final GetPropertyByIdUseCase getPropertyByIdUseCase;
    private final CreatePropertyUseCase createPropertyUseCase;

    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;


    public PropertyController(GetPropertyByIdUseCase getPropertyByIdUseCase, CreatePropertyUseCase createPropertyUseCase, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.getPropertyByIdUseCase = getPropertyByIdUseCase;
        this.createPropertyUseCase = createPropertyUseCase;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable String id) { // TODO: Consider to change 'id' arg to DTO.
//        var property = getPropertyByIdUseCase.execute(PropertyIdMapper.toDomain(id));
        var property = getPropertyByIdUseCase.execute(new PropertyId(UUID.fromString(id)));

        return transactionManager.doAfterCommitWithResult(() -> {
            return ResponseEntity.ok(PropertyMapper.toDto(property));
        });
    }

    @PostMapping
    public ResponseEntity<String> createProperty(@RequestBody PropertyDto propertyDto) {
        var property = createPropertyUseCase.execute(PropertyMapper.toDomain(propertyDto));

        return transactionManager.doAfterCommitWithResult(() -> {
            eventPublisher.publish(new PropertyCreatedDomainEvent(property.getId(), property.getOwnerId()));
            return ResponseEntity.status(HttpStatus.CREATED).body("Property created successfully"); // TODO: Consider to return domain object.
        });
    }
}
