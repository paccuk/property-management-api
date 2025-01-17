package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects;

import lombok.NonNull;
import lombok.Value;

@Value
public class LoginResponse {
    String token;
    long expiresIn;

    private LoginResponse(@NonNull String token, @NonNull Long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public static LoginResponse of(@NonNull String token, @NonNull Long expiresIn) {
        return new LoginResponse(token, expiresIn);
    }
}