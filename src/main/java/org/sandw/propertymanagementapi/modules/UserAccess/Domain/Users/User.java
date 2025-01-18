package org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users;

import lombok.Getter;
import org.sandw.propertymanagementapi.buildingblocks.Domain.AggregateRoot;
import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
import org.sandw.propertymanagementapi.modules.Properties.Domain.SharedKernel.SystemClock;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class User extends Entity implements AggregateRoot {
    private final UserId id;

    private final String firstName;
    private final String lastName;

    private final String email; // TODO: Consider to use value object for email.
    private final String phone; // TODO: Consider to use value object for phone number.

    private final LocalDateTime createdDate;

    private User(
            UserId id,
            String firstName,
            String lastName,
            String email,
            String phone,
            LocalDateTime createdDate
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.createdDate = createdDate;
    }


    public static User createNew(String firstName, String lastName, String email, String phone) {
        return new User(
                new UserId(UUID.randomUUID()),
                firstName,
                lastName,
                email,
                phone,
                SystemClock.now()
        );
    }

    public static User createNew(UserId id, String firstName, String lastName, String email, String phone, LocalDateTime createdDate) {
        return new User(
                id,
                firstName,
                lastName,
                email,
                phone,
                createdDate
        );
    }
}
