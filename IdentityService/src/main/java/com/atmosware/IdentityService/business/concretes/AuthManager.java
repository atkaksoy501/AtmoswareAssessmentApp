package com.atmosware.IdentityService.business.concretes;

import com.atmosware.Core.exceptions.types.BusinessException;
import com.atmosware.Core.util.JwtService;
import com.atmosware.IdentityService.business.abstracts.AuthService;
import com.atmosware.IdentityService.business.abstracts.UserService;
import com.atmosware.IdentityService.business.dtos.requests.LoginRequest;
import com.atmosware.IdentityService.business.messages.AuthMessages;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if(!authentication.isAuthenticated())
            throw new BusinessException(AuthMessages.LOGIN_FAILED);

        UserDetails user = userService.loadUserByUsername(request.getEmail());

        return jwtService.generateToken(user.getUsername(), user.getAuthorities() == null ? null : user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }
}
