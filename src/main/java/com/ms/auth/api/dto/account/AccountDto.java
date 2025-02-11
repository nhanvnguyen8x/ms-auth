package com.ms.auth.api.dto.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
