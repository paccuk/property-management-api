package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users.CreateUserUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("userUseCasesConfiguration")
public class userUseCasesConfig {

    private final UserRepository userRepository;

    public userUseCasesConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(userRepository);
    }
}
