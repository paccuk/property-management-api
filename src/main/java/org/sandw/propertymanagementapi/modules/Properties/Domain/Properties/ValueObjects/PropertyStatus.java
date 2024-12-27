package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;

import lombok.*;

@Value
public class PropertyStatus {

    public static final PropertyStatus Available = new PropertyStatus("Available");
    public static final PropertyStatus PartiallyOccupied = new PropertyStatus("PartiallyOccupied");
    public static final PropertyStatus FullyOccupied = new PropertyStatus("FullyOccupied");

    @With
    @Getter(AccessLevel.PRIVATE)
    String status;

    private PropertyStatus(@NonNull String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
