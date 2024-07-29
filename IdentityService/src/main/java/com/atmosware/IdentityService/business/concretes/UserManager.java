package com.atmosware.IdentityService.business.concretes;

import com.atmosware.Core.exceptions.types.BusinessException;
import com.atmosware.IdentityService.business.abstracts.UserService;
import com.atmosware.IdentityService.business.messages.AuthMessages;
import com.atmosware.IdentityService.dataAccess.abstracts.UserRepository;
import com.atmosware.IdentityService.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(User user) {
        // TODO: Business Rule, Validation
//        User user = modelMapperService.forRequest().map(request,User.class);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedDate(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByEmail(username).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserByEmail(username)
                .orElseThrow(() -> new BusinessException(AuthMessages.LOGIN_FAILED));
    }
}
