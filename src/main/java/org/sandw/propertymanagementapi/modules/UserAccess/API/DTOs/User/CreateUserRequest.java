package org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User;

public record CreateUserRequest(
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
