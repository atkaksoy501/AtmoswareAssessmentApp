package com.atmosware.IdentityService.business.abstracts;

import com.atmosware.IdentityService.business.dtos.requests.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}
