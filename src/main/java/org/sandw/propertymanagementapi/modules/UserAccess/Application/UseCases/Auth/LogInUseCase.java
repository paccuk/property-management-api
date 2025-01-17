package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.LoginUserDto;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth.AuthenticationService;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;

public class LogInUseCase {
    private final AuthenticationService authenticationService;

    public LogInUseCase(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public User execute(LoginUserDto user) {
        return authenticationService.authenticate(user);
    }
}
