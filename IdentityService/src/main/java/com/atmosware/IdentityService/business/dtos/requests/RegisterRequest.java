package com.atmosware.IdentityService.business.dtos.requests;

import com.atmosware.IdentityService.entities.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest
{
    private String email;
    private String password;
    private Role role;
}