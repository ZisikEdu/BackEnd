package org.zisik.edu.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.zisik.edu.config.AppConfig;
import org.zisik.edu.exception.ValidationException;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "id")
public class LocalAccount extends Account {

    private static final int PASSWORD_MAX_LENGTH = 8;

    public void validate() {
        if (accountId == null || accountId.isBlank()) {
            throw new ValidationException("아이디는 필수입니다.");
        }
        if (email == null || !email.contains("@")) {
            throw new ValidationException("유효하지 않은 이메일입니다.");
        }
        if (password == null || password.length() < PASSWORD_MAX_LENGTH) {
            throw new ValidationException("비밀번호는 최소 8자 이상이어야 합니다.");
        }
    }

    public void changePassword(String rawPassword, AppConfig.PasswordEncryptor encryptor) {
        if (rawPassword == null || rawPassword.length() < PASSWORD_MAX_LENGTH) {
            throw new ValidationException("비밀번호는 최소 8자 이상이어야 합니다.");
        }
        this.password = encryptor.encode(rawPassword);
        this.updateAt = LocalDateTime.now();
    }
    public boolean isPasswordValid(String rawPassword, AppConfig.PasswordEncryptor encryptor) {
        return encryptor.matches(rawPassword, this.password);
    }
}
