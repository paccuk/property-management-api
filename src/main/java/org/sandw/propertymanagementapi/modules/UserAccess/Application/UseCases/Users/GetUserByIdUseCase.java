package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;

public class GetUserByIdUseCase {
    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UserId userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
