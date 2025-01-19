package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UpdateUserRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Users.UserMapper;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.Events.UserUpdatedDomainEvent;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.UserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;

import java.util.UUID;

public class UpdateUserByIdUseCase {
    private final UserRepository userRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public UpdateUserByIdUseCase(UserRepository userRepository, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }


    public UserResponse execute(String id, UpdateUserRequest updateUserRequest) {
        return transactionManager.doInTransactionWithResult(() -> {
            var userId = new UserId(UUID.fromString(id));

            var existingUser = userRepository.findById(userId).orElseThrow();
            var mergedUser = mergeUsers(existingUser, updateUserRequest);
            var updatedUser = userRepository.update(mergedUser).orElseThrow();

            eventPublisher.publish(new UserUpdatedDomainEvent(userId));

            return UserMapper.toResponseDto(updatedUser);
        });
    }

    private User mergeUsers(User existingUser, UpdateUserRequest updateUserRequest) {
        updateUserRequest.getFirstName().ifPresent(existingUser::updateFirstName);
        updateUserRequest.getLastName().ifPresent(existingUser::updateLastName);
        updateUserRequest.getEmail().ifPresent(existingUser::updateEmail);
        updateUserRequest.getPhone().ifPresent(existingUser::updatePhone);

        return existingUser;
    }
}
