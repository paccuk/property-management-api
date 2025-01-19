package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Users;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users.*;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.UserRepository;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("userUseCasesConfiguration")
public class UserUseCasesConfig {
    private final UserRepository userRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public UserUseCasesConfig(UserRepository userRepository, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(userRepository, transactionManager, eventPublisher);
    }

    @Bean
    public UpdateUserByIdUseCase updateUserByIdUseCase() {
        return new UpdateUserByIdUseCase(userRepository, transactionManager, eventPublisher);
    }

    @Bean
    public DeleteUserByIdUseCase deleteUserByIdUseCase() {
        return new DeleteUserByIdUseCase(userRepository, transactionManager, eventPublisher);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase() {
        return new GetUserByIdUseCase(userRepository);
    }

    @Bean
    public GetUserByEmailUseCase getUserByEmailUseCase() {
        return new GetUserByEmailUseCase(userRepository);
    }
}
