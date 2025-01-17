package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(UserId userId);

    Optional<User> findByEmail(String email);

}
