package org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth;


public record SignUpRequest(
        String email,
        String password
) {
}
