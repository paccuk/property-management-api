package org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
