package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.JwtAuthenticationResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.SignInRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth.AuthUserService;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUser;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.JWT.JwtService;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SigninUserUseCase {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public SigninUserUseCase(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public JwtAuthenticationResponse execute(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.email(), signInRequest.password()
        ));

        var authUser = userDetailsService.loadUserByUsername(signInRequest.email());
        var jwt = jwtService.generateToken(authUser);

        return new JwtAuthenticationResponse(jwt);
    }
}
