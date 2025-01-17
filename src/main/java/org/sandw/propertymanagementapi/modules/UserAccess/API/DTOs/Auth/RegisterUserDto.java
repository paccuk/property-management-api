package org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth;


public record RegisterUserDto(
        String name,
        String firstName,
        String lastName,
        String password,
        String email,
        String phone
) {
}
