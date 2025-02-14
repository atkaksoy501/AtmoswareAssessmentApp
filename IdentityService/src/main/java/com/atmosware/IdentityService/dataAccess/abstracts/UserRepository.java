package com.atmosware.IdentityService.dataAccess.abstracts;

import com.atmosware.IdentityService.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findUserByEmail(String email);
}
