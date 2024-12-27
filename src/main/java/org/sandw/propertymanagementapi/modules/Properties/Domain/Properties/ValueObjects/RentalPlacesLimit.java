package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;

import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
public class RentalPlacesLimit {

    @With
    Integer value;

    public RentalPlacesLimit(@NonNull Integer value) {
        this.value = value;
    }
}
