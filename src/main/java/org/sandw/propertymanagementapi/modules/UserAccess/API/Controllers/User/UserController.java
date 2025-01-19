package org.sandw.propertymanagementapi.modules.UserAccess.API.Controllers.User;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UpdateUserRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.User.UserResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Users.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserByIdUseCase updateUserByIdUseCase;
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByEmailUseCase getUserByEmailUseCase;

    public UserController(CreateUserUseCase createUserUseCase, UpdateUserByIdUseCase updateUserByIdUseCase, DeleteUserByIdUseCase deleteUserByIdUseCase, GetUserByIdUseCase getUserByIdUseCase, GetUserByEmailUseCase getUserByEmailUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserByIdUseCase = updateUserByIdUseCase;
        this.deleteUserByIdUseCase = deleteUserByIdUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }


    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        var createdUser = createUserUseCase.execute(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<UserResponse> update(@PathVariable String id, @RequestBody UpdateUserRequest updateUserRequest) {
        var updatedUser = updateUserByIdUseCase.execute(id, updateUserRequest);
        return ResponseEntity.ok().body(updatedUser);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable String id) {
        deleteUserByIdUseCase.execute(id);
        return ResponseEntity.ok("Deleted user with id " + id);
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
