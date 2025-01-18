package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Auth;


import java.util.UUID;

public record AuthUserDto(
        UUID id,
        String email,
        String password,
        String role
) {
}
