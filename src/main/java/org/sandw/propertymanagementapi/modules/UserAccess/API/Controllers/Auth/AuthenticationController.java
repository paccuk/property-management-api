package org.sandw.propertymanagementapi.modules.UserAccess.API.Controllers.Auth;

import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.JwtAuthenticationResponse;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.SignInRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.API.DTOs.Auth.SignUpRequest;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth.SigninUserUseCase;
import org.sandw.propertymanagementapi.modules.UserAccess.Application.UseCases.Auth.SignupUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final SignupUserUseCase signupUserUseCase;
    private final SigninUserUseCase signinUserUseCase;

    public AuthenticationController(SignupUserUseCase signupUserUseCase, SigninUserUseCase signinUserUseCase) {
        this.signupUserUseCase = signupUserUseCase;
        this.signinUserUseCase = signinUserUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        System.out.println(signUpRequest);
        var registeredUser = signupUserUseCase.execute(signUpRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        var signedInUser = signinUserUseCase.execute(signInRequest);
        return ResponseEntity.ok(signedInUser);
    }
}