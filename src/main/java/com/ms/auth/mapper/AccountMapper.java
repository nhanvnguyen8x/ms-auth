package com.ms.auth.mapper;

import com.ms.auth.api.dto.account.AccountDto;
import com.ms.auth.api.dto.account.CreateAccountRequest;
import com.ms.auth.domain.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMapper {

    public List<AccountDto> toDtos(List<Account> accounts) {
        return accounts.stream().map(this::toDto).toList();
    }

    public AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .build();
    }

    public Account toEntity(CreateAccountRequest request) {
        return Account.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .build();
    }


}
