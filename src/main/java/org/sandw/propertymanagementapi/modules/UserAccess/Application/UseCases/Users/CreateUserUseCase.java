package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Users.UserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.Events.UserCreatedDomainEvent;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.UserRepository;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;

public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public CreateUserUseCase(UserRepository userRepository, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    public UserResponse execute(UserRequest createUserRequest) {
        return transactionManager.doInTransactionWithResult(() -> {

            var user = UserMapper.toDomain(createUserRequest);
            var createdUser = userRepository.save(user);

            eventPublisher.publish(new UserCreatedDomainEvent(createdUser.getId()));

            return UserMapper.toResponseDto(createdUser);
        });
    }
}
