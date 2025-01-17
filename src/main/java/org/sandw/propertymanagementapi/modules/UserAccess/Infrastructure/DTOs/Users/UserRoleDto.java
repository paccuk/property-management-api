package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users;

import java.util.UUID;

public record UserRoleDto(
        UUID id,
        String name
) {
}
