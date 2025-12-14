package org.zisik.edu.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.zisik.edu.config.AppConfig;
import org.zisik.edu.config.constant.AccountConstant;
import org.zisik.edu.config.constant.AccountMessage;
import org.zisik.edu.exception.ValidationException;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "id")
public class LocalAccount extends Account {

    private static final int PASSWORD_MIN_LENGTH = 8; //TODo 제거
    private final static String EMAIL_SEPARATOR = "@";

    public void validate() {
        //id
        if (accountId == null || accountId.isBlank()) {
            throw new jakarta.validation.ValidationException(AccountMessage.ID_BLANK);
        }
        if (accountId.length() > AccountConstant.ID_MAX_LENGTH) {
            throw new jakarta.validation.ValidationException(AccountMessage.ID_MAX_LENGTH_MSG);
        }
        if (accountId.length() < AccountConstant.ID_MIN_LENGTH) {
            throw new jakarta.validation.ValidationException(AccountMessage.ID_MIN_LENGTH_MSG);
        }
        //email
        if (email == null || !email.contains(EMAIL_SEPARATOR)) {
            throw new jakarta.validation.ValidationException(AccountMessage.EMAIL_VALIDATE);
        }

        //password
        if (password.length() < AccountConstant.PASSWORD_MIN_LENGTH) {
            throw new jakarta.validation.ValidationException(AccountMessage.PASSWORD_MIN_LENGTH_MSG);
        }
        if (password.length() < AccountConstant.PASSWORD_MAX_LENGTH) {
            throw new jakarta.validation.ValidationException(AccountMessage.PASSWORD_MAX_LENGTH_MSG);
        }
        //TODO 특수문자 공백 등등 추개검증 필요
    }

    public void changePassword(String rawPassword, AppConfig.PasswordEncryptor encryptor) {
        if (rawPassword == null || rawPassword.length() < PASSWORD_MIN_LENGTH) {
            throw new ValidationException("비밀번호는 최소 8자 이상이어야 합니다.");
        }
        this.password = encryptor.encode(rawPassword);
        this.updateAt = LocalDateTime.now();
    }
    public boolean isPasswordValid(String rawPassword, AppConfig.PasswordEncryptor encryptor) {
        return encryptor.matches(rawPassword, this.password);
    }
}
