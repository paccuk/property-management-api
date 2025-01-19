package org.sandw.propertymanagementapi.modules.UserAccess.Domain.User;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.User.ValueObjects.UserId;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> update(User mergedUser);

    void delete(UserId userId);

    Optional<User> findById(UserId userId);

    Optional<User> findByEmail(String email);
}
