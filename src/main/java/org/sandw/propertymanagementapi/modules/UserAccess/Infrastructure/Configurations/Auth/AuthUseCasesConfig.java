package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth.AuthenticationService;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth.LogInUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth.SignUpUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("authUseCasesConfiguration")
public class AuthUseCasesConfig {
    private final AuthenticationService authenticationService;

    public AuthUseCasesConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public SignUpUseCase signUpUseCase() {
        return new SignUpUseCase(authenticationService);
    }

    @Bean
    public LogInUseCase logInUseCase() {
        return new LogInUseCase(authenticationService);
    }
}
