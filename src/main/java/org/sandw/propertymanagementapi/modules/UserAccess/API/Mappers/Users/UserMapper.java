package org.sandw.propertymanagementapi.modules.UserAccess.API.Mappers.Users;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.RegisterUserDto;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {
    public static User registerDtoToDomain(RegisterUserDto userDto, PasswordEncoder passwordEncoder) {
        return User.createUser(
                userDto.firstName(),
                userDto.lastName(),
                passwordEncoder.encode(userDto.password()),
                userDto.email(),
                userDto.phone()
        );
    }
}
