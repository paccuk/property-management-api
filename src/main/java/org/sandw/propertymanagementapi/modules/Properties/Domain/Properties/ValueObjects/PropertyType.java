package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;


import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
public class PropertyType {

    @With
    @NonNull
    String type;

    private PropertyType(@NonNull String name) {
        this.type = name;
    }

    public static PropertyType of(@NonNull String name) {
        return new PropertyType(name);
    }
}