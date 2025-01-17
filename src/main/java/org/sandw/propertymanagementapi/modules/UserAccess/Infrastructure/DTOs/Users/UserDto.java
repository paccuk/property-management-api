package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String password,
        String email,
        String phone,
        List<String> roles,
        LocalDateTime createdDate
) {
}
