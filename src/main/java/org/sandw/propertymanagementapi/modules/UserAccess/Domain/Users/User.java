package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.AggregateRoot;
import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
import org.sandw.propertymanagementapi.modules.Properties.Domain.SharedKernel.SystemClock;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserName;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserRole;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class User extends Entity implements AggregateRoot, UserDetails {
    private final UserId id;

    private UserName name;

    private String firstName;
    private String lastName;
    private String password;

    private String email; // TODO: Consider to use value object for email.
    private String phone; // TODO: Consider to use value object for phone number.

    private List<UserRole> roles;

    private final LocalDateTime createdDate;


    public static User createAdmin(
            UserName name,
            String firstName,
            String lastName,
            String password,
            String email,
            String phone
    ) {
        return new User(
                new UserId(UUID.randomUUID()),
                name,
                firstName,
                lastName,
                password,
                email,
                phone,
                List.of(UserRole.Administrator),
                SystemClock.now()
        );
    }

    public static User createUser(
            String firstName,
            String lastName,
            String password,
            String email,
            String phone
    ) {
        return new User(
                new UserId(UUID.randomUUID()),
                UserName.of(firstName, lastName),
                firstName,
                lastName,
                password,
                email,
                phone,
                List.of(UserRole.User),
                SystemClock.now()
        );
    }

    public static User createNew(
            UserId id,
            UserName name,
            String firstName,
            String lastName,
            String password,
            String email,
            String phone,
            List<UserRole> roles,
            LocalDateTime createdDate
    ) {
        return new User(
                id,
                name,
                firstName,
                lastName,
                password,
                email,
                phone,
                roles,
                createdDate
        );
    }

    private User(
            UserId id,
            UserName name,
            String firstName,
            String lastName,
            String password,
            String email,
            String phone,
            List<UserRole> roles,
            LocalDateTime createdDate
    ) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.createdDate = createdDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }
}
