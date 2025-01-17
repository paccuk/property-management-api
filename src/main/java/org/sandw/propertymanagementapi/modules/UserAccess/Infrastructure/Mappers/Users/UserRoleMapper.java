package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Mappers.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserRole;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserRoleDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserRoleMapper {
    public static List<UserRole> toDomainList(List<UserRoleDto> roles) {
        return roles.stream().map(role -> UserRole.of(role.name())).collect(Collectors.toList());
    }

    public static List<UserRole> toDomainListString(List<String> roles) { // TODO: Consider to refactor logic with user role id.
        return roles.stream().map(UserRole::of).collect(Collectors.toList());
    }

    public static List<String> toDtoList(List<UserRole> roles) {
        return roles.stream().map(UserRole::getValue).collect(Collectors.toList());
    }
}
