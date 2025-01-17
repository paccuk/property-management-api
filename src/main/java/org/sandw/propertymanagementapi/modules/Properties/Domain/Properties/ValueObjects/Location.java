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

    private Location(@NonNull String address, @NonNull String city, @NonNull String postalCode) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }

    public static Location of(@NonNull String address, @NonNull String city, @NonNull String postalCode) {
        return new Location(address, city, postalCode);
    }
}