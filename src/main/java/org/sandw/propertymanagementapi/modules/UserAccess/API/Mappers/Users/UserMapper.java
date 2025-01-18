package org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.CreateUserRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;

public class UserMapper {
    public static User toDomain(CreateUserRequest createUserRequest) {
        return User.createNew(
                createUserRequest.firstName(),
                createUserRequest.lastName(),
                createUserRequest.email(),
                createUserRequest.phone()
        );
    }

    public static UserResponse toResponseDto(User user) {
        return new UserResponse(
                user.getId().getValue().toString(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getCreatedDate().toString()
        );
    }
}
