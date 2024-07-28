package com.atmosware.IdentityService.business.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerCreatedEvent {



    @NotNull
    private String email;


}
