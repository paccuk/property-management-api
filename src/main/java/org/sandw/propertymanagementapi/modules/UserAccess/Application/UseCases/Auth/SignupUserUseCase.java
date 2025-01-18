package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.JwtAuthenticationResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.SignUpRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Auth.AuthUserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth.AuthUserService;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUser;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.JWT.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignupUserUseCase {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthUserService authUserService;

    public SignupUserUseCase(PasswordEncoder passwordEncoder, JwtService jwtService, AuthUserService authUserService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authUserService = authUserService;
    }


    public JwtAuthenticationResponse execute(SignUpRequest signUpRequest) {
        String hashedPassword = passwordEncoder.encode(signUpRequest.password());
        AuthUser authUser = AuthUserMapper.toDomain(signUpRequest, hashedPassword);

        authUserService.create(authUser);
        var jwt = jwtService.generateToken(authUser);

        return new JwtAuthenticationResponse(jwt);
    }
}
