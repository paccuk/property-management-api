package org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUser;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.AuthUserRepository;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.Events.UserRegisteredDomainEvent;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    public AuthUserService(AuthUserRepository authUserRepository, TransactionManager transactionManager, EventPublisher eventPublisher) {
        this.authUserRepository = authUserRepository;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
    }

    public AuthUser save(AuthUser authUser) {
        return transactionManager.doInTransactionWithResult(() -> {
            AuthUser savedUser = authUserRepository.save(authUser);
            eventPublisher.publish(new UserRegisteredDomainEvent(authUser.getId()));
            return savedUser;
        });
    }

    public AuthUser create(AuthUser authUser) {
        if (authUserRepository.existsByEmail(authUser.getEmail())) {
            throw new RuntimeException("User with email " + authUser.getEmail() + " already exists"); // TODO: Create custom exceptions
        }

        return save(authUser);
    }

    public AuthUser getByEmail(String email) {
        return authUserRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + "not found.")
                );
    }
}
