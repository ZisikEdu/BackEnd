package org.zisik.edu.user.domain;

import com.nimbusds.oauth2.sdk.TokenIntrospectionSuccessResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthAccount extends Account {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String provider;
    private String providerUserid;
    private String access_token;
    private String refresh_token;
    private String linkedAt;

    @Builder
    public OAuthAccount(
            String accountId,
            String password,
            String email,
            String username,
            UserProfile userProfile,
            UserProfile profile,
            Role role,
            String provider,
            String providerUserid,
            String linkedAt)
    {
        this.accountId = accountId;
        this.password = passwordEncoder.encode(password);
        this.email = email;
        this.username = username;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.userProfile = userProfile;
        this.profile = profile;
        this.role = role;
        this.provider = provider;
        this.providerUserid = providerUserid;
        this.linkedAt = linkedAt;
    }
}
