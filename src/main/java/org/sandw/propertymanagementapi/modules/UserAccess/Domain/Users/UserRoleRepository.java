package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserRoleDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository {
    void assignRolesByUserId(List<UUID> userRoleId, UUID userId);

    void assignRoleByUserId(UUID userRoleId, UUID userId);

    void removeRolesByUserId(List<UUID> userRoleIds, UUID userId);

    void removeRoleByUserId(UUID userRoleId, UUID userId);

    Optional<UserRoleDto> findRoleByName(String roleName);

    List<UserRoleDto> findRolesByNames(List<String> names);

    List<UserRoleDto> findRolesByUserId(UserId userId);
}
