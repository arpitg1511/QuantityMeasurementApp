package com.app.quantitymeasurement.security.config;

import com.app.quantitymeasurement.user.entity.User;
import com.app.quantitymeasurement.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // In a microservice environment, we can trust the email from the JWT
        // if it has been validated by JwtService (signature check).
        // Returning a generic UserDetails to avoid database dependency.
        return new org.springframework.security.core.userdetails.User(
                email,
                "", // Password not needed for already authenticated requests
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
