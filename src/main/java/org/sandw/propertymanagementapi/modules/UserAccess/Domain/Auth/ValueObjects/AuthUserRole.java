package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects;

import lombok.NonNull;
import lombok.Value;
import lombok.With;


@Value
public class AuthUserRole {

    public static final AuthUserRole Administrator = new AuthUserRole("ROLE_ADMIN");
    public static final AuthUserRole User = new AuthUserRole("ROLE_USER");

    @With
    String value;

    private AuthUserRole(@NonNull String value) {
        this.value = value;
    }

    public static AuthUserRole of(@NonNull String value) {
        return new AuthUserRole(value);
    }
}
