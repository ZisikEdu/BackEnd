package org.zisik.edu.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@PrimaryKeyJoinColumn(name = "id")
public class LocalAccount extends Account {

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Builder
    public LocalAccount(String accountId, String password, String email, String username, LocalDateTime createAt, LocalDateTime updateAt, UserProfile userProfile, UserProfile profile, Role role) {
        this.accountId = accountId;
        this.password = passwordEncoder.encode(password);
        this.email = email;
        this.username = username;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.userProfile = userProfile;
        this.profile = profile;
        this.role = role;
    }
}
