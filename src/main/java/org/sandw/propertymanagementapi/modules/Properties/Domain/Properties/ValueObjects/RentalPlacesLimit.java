package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;

import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
public class RentalPlacesLimit {

    @With
    Integer value;

    private RentalPlacesLimit(@NonNull Integer value) {
        this.value = value;
    }

    public static RentalPlacesLimit of(@NonNull Integer value) {
        return new RentalPlacesLimit(value);
    }
}
