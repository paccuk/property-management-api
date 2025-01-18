package org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User;

public record UserResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String createdDate
) {
}
