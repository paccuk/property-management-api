package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.AggregateRoot;
import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects.AuthUserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects.AuthUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
public class AuthUser extends Entity implements AggregateRoot, UserDetails {
    private final AuthUserId id;
    private final String email;
    private final String password;
    private final AuthUserRole role;

    public AuthUser(AuthUserId id, String email, String password, AuthUserRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static AuthUser createNew(String email, String password, AuthUserRole role) {
        return new AuthUser(
                new AuthUserId(UUID.randomUUID()),
                email,
                password,
                role
        );
    }

    public static AuthUser createNew(AuthUserId id, String email, String password, AuthUserRole role) {
        return new AuthUser(
                id,
                email,
                password,
                role
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getValue()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
