package org.zisik.edu.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zisik.edu.user.domain.Account;
import org.zisik.edu.user.repository.AccountRepository;


//시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetaileService 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    //시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account accountEntity = accountRepository.findByUsername(username);
        if (accountEntity != null) {
            return new PrincipalDetails(accountEntity);
        }
        return null;
    }
}
