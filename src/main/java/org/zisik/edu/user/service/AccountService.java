package org.zisik.edu.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zisik.edu.user.domain.Account;
import org.zisik.edu.user.domain.OAuthAccount;
import org.zisik.edu.user.repository.AccountRepository;
import org.zisik.edu.exception.*;
import org.zisik.edu.user.response.AccountResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountResponse getUserProfile(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(UserNotFound::new);

        return new AccountResponse(account);
    }

    public Optional<Account> findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return Optional.ofNullable(account);
    }

    public void oauthJoin(OAuthAccount accountEntity) {
        accountRepository.save(accountEntity);
    }

    public Optional<Account> findByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        return Optional.ofNullable(account);
    }
}
