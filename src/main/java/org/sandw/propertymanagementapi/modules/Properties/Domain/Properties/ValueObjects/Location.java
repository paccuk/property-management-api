package org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects;

import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
public class Location {
    @With
    String address;
    @With
    String postalCode;
    @With
    String city;
    @With
    String state;

    private Location(@NonNull String street, @NonNull String city, @NonNull String state, @NonNull String postalCode) {
        this.address = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public static Location of(@NonNull String street, @NonNull String city, @NonNull String state, @NonNull String postalCode) {
        return new Location(street, city, state, postalCode);
    }
}