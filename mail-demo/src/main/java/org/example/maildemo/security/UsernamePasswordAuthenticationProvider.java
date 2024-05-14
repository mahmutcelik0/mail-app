package org.example.maildemo.security;

import lombok.RequiredArgsConstructor;
import org.example.maildemo.constants.CustomResponseMessage;
import org.example.maildemo.entity.User;
import org.example.maildemo.exception.NotFoundException;
import org.example.maildemo.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = null;
        try {
            user = userService.findUserByMail(authentication.getPrincipal().toString());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean matches = passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()); //Credentials == password

        if (!matches) try {
            throw new NotFoundException(CustomResponseMessage.WRONG_CREDENTIAL);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)); //copy from AbstractUserDetailsAuthProvider
    }
}

