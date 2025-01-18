package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth.AuthUserService;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetCurrentAuthUserUseCase {
    private final AuthUserService authUserService;

    public GetCurrentAuthUserUseCase(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    public AuthUser execute() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return authUserService.getByEmail(email);
    }
}
