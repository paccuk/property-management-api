package org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth;

public record LoginUserDto(
        String email,
        String password
) {
}
