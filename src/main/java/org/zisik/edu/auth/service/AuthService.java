package org.zisik.edu.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zisik.edu.auth.request.Login;
import org.zisik.edu.auth.request.SignUp;
import org.zisik.edu.exception.AlreadyExistsAccountException;
import org.zisik.edu.exception.InvalidPassword;
import org.zisik.edu.exception.UserNotFound;
import org.zisik.edu.user.domain.Account;
import org.zisik.edu.user.domain.LocalAccount;
import org.zisik.edu.user.domain.Role;
import org.zisik.edu.user.repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignUp signup) {
        Optional<Account> userOptional = accountRepository.findByAccountId(signup.getAccountId());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsAccountException();
        }

        String encryptedPassword = passwordEncoder.encode(signup.getPassword());

        accountRepository.save(LocalAccount.builder()
                .accountId(signup.getAccountId())
                .username(signup.getUsername())
                .password(encryptedPassword)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .email(signup.getEmail())
                .profile(null)
                .role(Role.USER)
                .build());

        System.out.println("signup accountId: " + signup.getAccountId());
        System.out.println("signup email: " + signup.getEmail());
        System.out.println("signup name: " + signup.getUsername());
        System.out.println("signup password: " + signup.getPassword());
    }

    public void login(Login login) {
        // 사용자 존재 확인
        Account account = accountRepository.findByAccountId(login.getAccountId())
                .orElseThrow(() -> {
                    System.out.printf("존재하지 않는 계정 - accountId: {}", login.getAccountId());
                    return new UserNotFound();
                });

        // 비밀번호 검증
        if (!passwordEncoder.matches(login.getPassword(), account.getPassword())) {
            System.out.printf("비밀번호 불일치 - accountId: {}", login.getAccountId());
            throw new InvalidPassword();
        }

        System.out.printf("수동 로그인 검증 성공 - accountId: {}, email: {}",
                account.getAccountId(), account.getEmail());
        //return responsetn
    }
}
