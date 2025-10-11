package org.zisik.edu.user.repository;


import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.user.domain.Account;

import java.util.Optional;

//@Repository 가 없어도 IoC가 됩니다. JpaRepository를 상속받았기 떄문
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    Optional<Account> findByAccountId(@NotBlank(message = "아이디 입력해주세요") String accountId);
    Account findByEmail(String email);

    Object existsByAccountId(String accountId);

    Object existsByEmail(String email);
}
