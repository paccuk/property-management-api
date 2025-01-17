package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRepository;

public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        userRepository.save(user);
        return user;
    }
}
