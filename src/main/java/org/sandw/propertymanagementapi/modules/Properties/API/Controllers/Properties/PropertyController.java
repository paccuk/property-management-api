package org.sandw.propertymanagementapi.modules.Properties.API.Controllers.Properties;

import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.CreatePropertyRequest;
import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.PropertyResponse;
import org.sandw.propertymanagementapi.modules.Properties.API.DTOs.Properties.UpdatePropertyRequest;
import org.sandw.propertymanagementapi.modules.Properties.Application.UseCases.Properties.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private final CreatePropertyUseCase createPropertyUseCase;
    private final GetPropertyByIdUseCase getPropertyByIdUseCase;
    private final GetPropertyByOwnerIdUseCase getPropertyByUserIdUseCase;
    private final DeletePropertyByIdUseCase deletePropertyByIdUseCase;
    private final UpdatePropertyByIdUseCase updatePropertyByIdUseCase;

    public PropertyController(
            CreatePropertyUseCase createPropertyUseCase,
            GetPropertyByIdUseCase getPropertyByIdUseCase,
            GetPropertyByOwnerIdUseCase getPropertyByUserIdUseCase,
            DeletePropertyByIdUseCase deletePropertyByIdUseCase,
            UpdatePropertyByIdUseCase updatePropertyByIdUseCase
    ) {
        this.createPropertyUseCase = createPropertyUseCase;
        this.getPropertyByIdUseCase = getPropertyByIdUseCase;
        this.getPropertyByUserIdUseCase = getPropertyByUserIdUseCase;
        this.deletePropertyByIdUseCase = deletePropertyByIdUseCase;
        this.updatePropertyByIdUseCase = updatePropertyByIdUseCase;
    }


    @PostMapping("/create")
    public ResponseEntity<PropertyResponse> createProperty(@RequestBody CreatePropertyRequest propertyRequest) {
        var createdProperty = createPropertyUseCase.execute(propertyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<PropertyResponse> updateProperty(@PathVariable String id, @RequestBody UpdatePropertyRequest propertyUpdateRequest) {
        var updatedProperty = updatePropertyByIdUseCase.execute(id, propertyUpdateRequest);
        return ResponseEntity.ok(updatedProperty);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<String> deleteProperty(@PathVariable String id) {
        deletePropertyByIdUseCase.execute(id);
        return ResponseEntity.ok("Deleted property with id " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable String id) {
        var property = getPropertyByIdUseCase.execute(id);
        return ResponseEntity.ok(property);
    }

    @GetMapping("/{owner-id}")
    public ResponseEntity<PropertyResponse> getPropertyByUserId(@PathVariable("owner-id") String ownerid) {
        var property = getPropertyByUserIdUseCase.execute(ownerid);
        return ResponseEntity.ok(property);
    }
}
