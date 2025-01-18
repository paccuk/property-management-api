package org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth;

public record SignInRequest(
        String email,
        String password
) {
}
