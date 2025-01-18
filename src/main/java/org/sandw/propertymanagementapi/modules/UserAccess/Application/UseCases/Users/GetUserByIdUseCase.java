package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Users.UserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;

import java.util.UUID;

public class GetUserByIdUseCase {
    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse execute(String id) {
        var userId = new UserId(UUID.fromString(id));
        var user = userRepository.findById(userId).orElseThrow(); // TODO: Add custom exception.
        return UserMapper.toResponseDto(user);
    }
}
