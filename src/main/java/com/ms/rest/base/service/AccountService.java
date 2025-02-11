package com.ms.rest.base.service;

import com.ms.rest.base.api.dto.account.AccountDto;
import com.ms.rest.base.api.dto.account.CreateAccountRequest;
import com.ms.rest.base.exception.BadRequestException;
import com.ms.rest.base.domain.Account;
import com.ms.rest.base.mapper.AccountMapper;
import com.ms.rest.base.repository.AccountRepository;
import com.ms.rest.base.service.auth.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TokenService tokenService;
    private final AccountMapper accountMapper;

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.toDtos(accounts);
    }

    public AccountDto createNewAccount(CreateAccountRequest request) {
        Account account = accountMapper.toEntity(request);

        return accountMapper.toDto(accountRepository.save(account));
    }

    public AccountDto getCurrentLoggedAccount(HttpHeaders httpHeaders) {
        String authorization = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);

        Long accountId = tokenService.extractAccountId(authorization);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BadRequestException("Account not found"));

        return accountMapper.toDto(account);
    }


}
