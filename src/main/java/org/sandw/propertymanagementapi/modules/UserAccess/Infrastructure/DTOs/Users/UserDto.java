package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDateTime createdDate
) {
}
