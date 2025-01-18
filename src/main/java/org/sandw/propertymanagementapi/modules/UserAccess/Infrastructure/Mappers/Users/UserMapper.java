package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Mappers.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserDto;

public class UserMapper {
    public static User toDomain(UserDto userDto) {
        return User.createNew(
                new UserId(userDto.id()),
                userDto.firstName(),
                userDto.lastName(),
                userDto.email(),
                userDto.phone(),
                userDto.createdDate()
        );
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId().getValue(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getCreatedDate()
        );
    }
}
