package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth;

import java.util.Optional;

public interface AuthUserRepository {
    AuthUser save(AuthUser authUser);

    Optional<AuthUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
