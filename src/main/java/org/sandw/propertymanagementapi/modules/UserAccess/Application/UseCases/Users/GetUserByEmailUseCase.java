package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Users.UserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.UserRepository;

public class GetUserByEmailUseCase {
    private final UserRepository userRepository;

    public GetUserByEmailUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse execute(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(); // TODO: Add custom exception.
        return UserMapper.toResponseDto(user);
    }
}
