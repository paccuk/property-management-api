package org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User;

import lombok.Getter;

import java.util.Optional;

@Getter
public class UpdateUserRequest {
    private final Optional<String> firstName = Optional.empty();
    private final Optional<String> lastName = Optional.empty();
    private final Optional<String> email = Optional.empty();
    private final Optional<String> phone = Optional.empty();
}
