package org.example.maildemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.maildemo.constants.CustomResponseMessage;
import org.example.maildemo.security.CustomUserDetailsService;
import org.example.maildemo.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtUtilities;
    private final CustomUserDetailsService customUserDetailsService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String token = jwtUtilities.getToken(request);

        if (token != null && jwtUtilities.validateToken(token)) {
            String subject = jwtUtilities.extractSubject(token);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(subject);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }else {
                throw new RuntimeException(CustomResponseMessage.ACCOUNT_BLOCKED);
            }
        }
        filterChain.doFilter(request, response);
    }

}
