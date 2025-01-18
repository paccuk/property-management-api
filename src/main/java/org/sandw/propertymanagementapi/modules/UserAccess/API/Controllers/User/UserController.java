package org.sandw.propertymanagementapi.modules.UserAccess.API.Controllers.User;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.CreateUserRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users.CreateUserUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users.GetUserByEmailUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users.GetUserByIdUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByEmailUseCase getUserByEmailUseCase;

    public UserController(CreateUserUseCase createUserUseCase, GetUserByIdUseCase getUserByIdUseCase, GetUserByEmailUseCase getUserByEmailUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest createUserRequest) {
        var createdUser = createUserUseCase.execute(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        var user = getUserByIdUseCase.execute(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        var user = getUserByEmailUseCase.execute(email);
        return ResponseEntity.ok(user);
    }
}
