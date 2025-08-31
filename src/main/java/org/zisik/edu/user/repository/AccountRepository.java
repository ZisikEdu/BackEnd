package org.zisik.edu.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.user.domain.Account;

//@Repository 가 없어도 IoC가 됩니다. JpaRepository를 상속받았기 떄문
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);
}
