package org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.Events.UserDeletedDomainEvent;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.UserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;

import java.util.UUID;

public class DeleteUserByIdUseCase {
    private final UserRepository userRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public DeleteUserByIdUseCase(UserRepository userRepository, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    public void execute(String id) {
        transactionManager.doInTransaction(() -> {
            var userId = new UserId(UUID.fromString(id));
            userRepository.delete(userId);

            eventPublisher.publish(new UserDeletedDomainEvent(userId));
        });
    }
}
