package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth.AuthUserService;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth.SigninUserUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth.SignupUserUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.JWT.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration("authUserCasesConfiguration")
public class AuthUserCasesConfig {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthUserService authUserService;

    public AuthUserCasesConfig(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthUserService authUserService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authUserService = authUserService;
    }

    @Bean
    public SignupUserUseCase signUpUseCase() {
        return new SignupUserUseCase(passwordEncoder, jwtService, authUserService);
    }

    @Bean
    public SigninUserUseCase logInUseCase() {
        return new SigninUserUseCase(authenticationManager, jwtService, userDetailsService);
    }
}
