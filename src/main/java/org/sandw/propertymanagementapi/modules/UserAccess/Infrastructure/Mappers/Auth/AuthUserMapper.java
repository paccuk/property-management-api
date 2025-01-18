package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Mappers.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUser;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects.AuthUserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects.AuthUserRole;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Auth.AuthUserDto;

public class AuthUserMapper {
    public static AuthUserDto toDto(AuthUser authUser) {
        return new AuthUserDto(
                authUser.getId().getValue(),
                authUser.getEmail(),
                authUser.getPassword(),
                authUser.getRole().getValue()
        );
    }

    public static AuthUser toDomain(AuthUserDto authUserDto) {
        return AuthUser.createNew(
                new AuthUserId(authUserDto.id()),
                authUserDto.email(),
                authUserDto.password(),
                AuthUserRole.of(authUserDto.role())
        );
    }
}
