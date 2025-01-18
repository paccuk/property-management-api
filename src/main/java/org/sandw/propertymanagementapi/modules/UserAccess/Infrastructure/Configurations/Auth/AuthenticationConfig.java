package org.sandw.propertymanagementapi.modules.UserAccess.Infrastructure.Configurations.Auth;


import org.sandw.propertymanagementapi.modules.UserAccess.Application.Services.Auth.AuthUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration("authenticationConfiguration")
public class AuthenticationConfig {

    private final AuthUserService authUserService;

    public AuthenticationConfig(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return authUserService::getByEmail;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
