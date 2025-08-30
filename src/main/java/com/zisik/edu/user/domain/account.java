package com.zisik.edu.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
/*@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")*/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String account_id; // 아이디

    private String password;

    private LocalDateTime createdAt;

    @Builder
    public account(String name, String account_id, String password) {
        this.name = name;
        this.account_id = account_id;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }
}
