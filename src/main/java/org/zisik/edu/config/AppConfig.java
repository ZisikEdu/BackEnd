package org.zisik.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
public class AppConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Component
    public static class SpringSecurityPasswordEncryptor implements PasswordEncryptor {
        private final PasswordEncoder delegate;

        public SpringSecurityPasswordEncryptor(PasswordEncoder delegate) {
            this.delegate = delegate;
        }

        @Override
        public String encode(String rawPassword) {
            return delegate.encode(rawPassword);
        }

        @Override
        public boolean matches(String rawPassword, String encodedPassword) {
            return delegate.matches(rawPassword, encodedPassword);
        }
    }

    public interface PasswordEncryptor {
        String encode(String rawPassword);
        boolean matches(String rawPassword, String encodedPassword);
    }
}
