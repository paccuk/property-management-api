package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;


import lombok.*;

@Value
public class PropertyType {

    @With
    String value;

    private PropertyType(@NonNull String value) {
        this.value = value;
    }

    public static PropertyType of(@NonNull String value) {
        return new PropertyType(value);
    }
}