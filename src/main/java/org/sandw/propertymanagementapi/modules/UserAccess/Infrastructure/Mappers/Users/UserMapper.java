package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Mappers.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserId;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserName;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.ValueObjects.UserRole;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserDto;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.DTOs.Users.UserRoleDto;

import java.util.List;

public class UserMapper {
    public static User toDomain(UserDto userDto, List<UserRoleDto> roleDtos) {
        return User.createNew(
                new UserId(userDto.id()),
                UserName.of(userDto.firstName(), userDto.lastName()),
                userDto.firstName(),
                userDto.lastName(),
                userDto.password(),
                userDto.email(),
                userDto.phone(),
                roleDtos.stream().map(role -> UserRole.of(role.name())).toList(),
                userDto.createdDate()
        );
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId().getValue(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getRoles().stream().map(UserRole::getValue).toList(),
                user.getCreatedDate()
        );
    }
}
