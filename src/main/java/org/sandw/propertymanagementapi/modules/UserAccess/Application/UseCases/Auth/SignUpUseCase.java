package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.RegisterUserDto;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth.AuthenticationService;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;

public class SignUpUseCase {
    private final AuthenticationService authenticationService;

    public SignUpUseCase(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public User execute(RegisterUserDto user) {
        return authenticationService.signup(user);
    }
}
