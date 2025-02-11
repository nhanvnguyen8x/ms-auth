package com.ms.auth.controller;

import com.ms.auth.api.dto.ApiResponse;
import com.ms.auth.api.dto.account.AccountDto;
import com.ms.auth.api.dto.account.CreateAccountRequest;
import com.ms.auth.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(ApiResponse.success(accountDtos));
    }

    @PostMapping
    private ResponseEntity<ApiResponse> create(@RequestBody CreateAccountRequest request) {
        return null;
    }

    @GetMapping("/current")
    public ResponseEntity<ApiResponse> getCurrentUserLogin(@RequestHeader HttpHeaders headers) {
        AccountDto accountDto = accountService.getCurrentLoggedAccount(headers);
        return ResponseEntity.ok(ApiResponse.success(accountDto));
    }
}
