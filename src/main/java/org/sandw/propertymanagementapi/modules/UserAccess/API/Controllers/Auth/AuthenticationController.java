package org.sandw.propertymanagementapi.modules.UserAccess.API.Controllers.Auth;

import org.sandw.propertymanagementapi.buildingblocks.Application.EventPublisher.EventPublisher;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.LoginUserDto;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.RegisterUserDto;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth.LogInUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth.SignUpUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Auth.ValueObjects.LoginResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.Auth.Events.UserRegisteredDomainEvent;
import org.sandw.propertymanagementapi.modules.UserAccess.Domain.Users.User;
import org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.JWT.JwtService;
import org.sandw.propertymanagementapi.modules.shared.Transactions.TransactionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final TransactionManager transactionManager;
    private final EventPublisher eventPublisher;

    private final SignUpUseCase signUpUseCase;
    private final LogInUseCase logInUseCase;

    public AuthenticationController(
            JwtService jwtService,
            TransactionManager transactionManager,
            EventPublisher eventPublisher,
            SignUpUseCase signUpUseCase,
            LogInUseCase logInUseCase
    ) {
        this.jwtService = jwtService;
        this.transactionManager = transactionManager;
        this.eventPublisher = eventPublisher;
        this.signUpUseCase = signUpUseCase;
        this.logInUseCase = logInUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) { // TODO: Consider if transactionManager is need here
        User registeredUser = signUpUseCase.execute(registerUserDto);

        return transactionManager.doInTransactionWithResult(() -> {
            eventPublisher.publish(new UserRegisteredDomainEvent(registeredUser.getId()));
            return ResponseEntity.ok(registeredUser);
        });
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = logInUseCase.execute(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.of(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}