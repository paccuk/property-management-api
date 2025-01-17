package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects;

import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value
public class UserName {

    @With
    String firstName;

    @With
    String lastName;

    private UserName(@NonNull String firstName, @NonNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserName of(@NonNull String firstName, @NonNull String lastName) {
        return new UserName(firstName, lastName);
    }
}
