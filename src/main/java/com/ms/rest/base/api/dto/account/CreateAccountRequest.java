package com.ms.rest.base.api.dto.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAccountRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
