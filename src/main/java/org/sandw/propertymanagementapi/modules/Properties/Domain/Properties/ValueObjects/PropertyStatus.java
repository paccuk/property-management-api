package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;

import lombok.*;

@Value
public class PropertyStatus {

    public static final PropertyStatus Available = new PropertyStatus("Available");
    public static final PropertyStatus PartiallyOccupied = new PropertyStatus("PartiallyOccupied");
    public static final PropertyStatus FullyOccupied = new PropertyStatus("FullyOccupied");

    @With
    String value;

    private PropertyStatus(@NonNull String value) {
        this.value = value;
    }

    public static PropertyStatus of(@NonNull String value) {
        return new PropertyStatus(value); // TODO: Handle if given status not exist.
    }
}
