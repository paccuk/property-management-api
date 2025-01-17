package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects;

import lombok.NonNull;
import lombok.Value;
import lombok.With;
import org.sandw.propertymanagementapi.modules.Properties.Domain.Properties.ValueObjects.PropertyStatus;


@Value
public class UserRole {

    public static final UserRole Administrator = new UserRole("Administrator");
    public static final UserRole User = new UserRole("User");

    @With
    String value;

    private UserRole(@NonNull String value) {
        this.value = value;
    }

    public static UserRole of(@NonNull String value) {
        return new UserRole(value);
    }
}
