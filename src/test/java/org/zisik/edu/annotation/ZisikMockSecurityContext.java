package org.zisik.edu.annotation;

import lombok.RequiredArgsConstructor;
import org.zisik.edu.config.auth.UserPrincipal;
import org.zisik.edu.user.domain.LocalAccount;
import org.zisik.edu.user.repository.AccountRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;


@RequiredArgsConstructor
public class ZisikMockSecurityContext implements WithSecurityContextFactory<ZisikMockUser> {
    private final AccountRepository accountRepository;
    @Override
    public SecurityContext createSecurityContext(ZisikMockUser annotation) {
        String username = annotation.account();
        String password = annotation.password();
        String name = annotation.name();
        String email = annotation.email();
        var user = LocalAccount.builder()
                .accountId(username)
                .password(password)
                .email(email)
                .username(name)
                .build();

        accountRepository.save(user);

        var principal = new UserPrincipal(user);

        var role = new SimpleGrantedAuthority("ROLE_ADMIN");

        var authenticationToken = new UsernamePasswordAuthenticationToken(principal, user.getPassword(), List.of(role));

        var context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authenticationToken);

        return context;
    }
}