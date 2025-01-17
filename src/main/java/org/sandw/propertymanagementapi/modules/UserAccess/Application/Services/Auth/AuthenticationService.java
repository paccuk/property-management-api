package org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.LoginUserDto;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.RegisterUserDto;
import org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Users.UserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public AuthenticationService(
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, UserRepository userRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User signup(RegisterUserDto userDto) {
        return UserMapper.registerDtoToDomain(userDto, passwordEncoder);
    }

    public User authenticate(LoginUserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.email(),
                        userDto.password()
                )
        );

        return userRepository.findByEmail(userDto.email())
                .orElseThrow(); // TODO: Handle
    }
}
