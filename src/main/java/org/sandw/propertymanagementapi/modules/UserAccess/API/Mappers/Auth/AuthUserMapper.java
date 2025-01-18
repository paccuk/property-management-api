package org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.SignUpRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUser;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects.AuthUserRole;

public class AuthUserMapper {
    public static AuthUser toDomain(SignUpRequest signUpRequest, String hashedPassword) {
        return AuthUser.createNew(
                signUpRequest.email(),
                hashedPassword,
                AuthUserRole.User
        );
    }
}
