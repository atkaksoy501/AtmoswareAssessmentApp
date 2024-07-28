package com.atmosware.IdentityService.business.abstracts;

import com.atmosware.IdentityService.entities.concretes.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(User user);
    User findByUsername(String username);
}
