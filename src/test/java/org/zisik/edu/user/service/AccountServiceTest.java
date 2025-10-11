package org.zisik.edu.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zisik.edu.user.domain.Account;
import org.zisik.edu.user.domain.LocalAccount;
import org.zisik.edu.user.repository.AccountRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private LocalAccount validAccount;
    private LocalAccount blankIdAccount;
    private LocalAccount shortPasswordAccount;

    @BeforeEach
    void setUp() {
        validAccount = LocalAccount.builder()
                .accountId("testUser")
                .email("test@example.com")
                .password("password123")
                .username("testerName1")
                .build();

        blankIdAccount = LocalAccount.builder()
                .accountId("   ") // 아이디 공백
                .email("test2@example.com")
                .password("password123")
                .username("testerName2")
                .build();

        shortPasswordAccount = LocalAccount.builder()
                .accountId("testUser3")
                .email("test3@example.com")
                .password("123") // 짧은 패스워드
                .username("testerName3")
                .build();
    }

    @Test
    void join_validAccount_success() {
        // given - 유효한 계정 정보와 중복되지 않는 상황 설정
        //given(accountRepository.existsByAccountId(validAccount.getAccountId())).willReturn(false);
        //given(accountRepository.existsByEmail(validAccount.getEmail())).willReturn(false);
        given(accountRepository.save(any(LocalAccount.class))).willReturn(validAccount);

        // when - 회원가입 실행
        Account result = accountService.join(validAccount);

        // then - 결과 검증
        assertThat(result).isNotNull();
        assertThat(result.getAccountId()).isEqualTo("testUser");
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        verify(accountRepository, times(1)).save(any(LocalAccount.class));
    }

    @Test
    void join_invalidAccount_success() {
        // given - 중복된 아이디가 이미 존재하는 상황
        //given(accountRepository.existsByAccountId(validAccount.getAccountId())).willReturn(true);

        // when & then - 예외 발생 검증
        assertThatThrownBy(() -> accountService.join(validAccount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 존재하는");
    }

    @Test
    void join_blankAccountId_throwsException() {
        // when & then - 공백 아이디로 가입 시도 시 예외 발생
        assertThatThrownBy(() -> accountService.join(blankIdAccount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아이디");
    }

    @Test
    void join_shortPassword_throwsException() {
        // when & then - 짧은 비밀번호로 가입 시도 시 예외 발생
        assertThatThrownBy(() -> accountService.join(shortPasswordAccount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비밀번호");
    }

    @Test
    void findByAccountId_success() {
        // given - 존재하는 계정 설정
        given(accountRepository.findByAccountId("testUser"))
                .willReturn(Optional.of(validAccount));

        // when - 아이디로 계정 조회
        Optional<Account> result = accountService.findByAccountId("testUser");

        // then - 조회 결과 검증
        assertThat(result).isPresent();
        assertThat(result.get().getAccountId()).isEqualTo("testUser");
        verify(accountRepository, times(1)).findByAccountId("testUser");
    }

    @Test
    void findByAccountId_notFound_returnsEmpty() {
        // given - 존재하지 않는 계정
        given(accountRepository.findByAccountId("nonExistent"))
                .willReturn(Optional.empty());

        // when - 존재하지 않는 아이디로 조회
        Optional<Account> result = accountService.findByAccountId("nonExistent");

        // then - 빈 Optional 반환 확인
        assertThat(result).isEmpty();
    }

    @Test
    void getUserProfile() {
        // given - 존재하는 사용자 프로필
        given(accountRepository.findByAccountId("testUser"))
                .willReturn(Optional.of(validAccount));

//        // when - 사용자 프로필 조회
//        Account result = accountService.getUserProfile(1L);
//
//        // then - 프로필 정보 검증
//        assertThat(result).isNotNull();
//        assertThat(result.getAccountId()).isEqualTo("testUser");
//        assertThat(result.getUsername()).isEqualTo("testerName1");
    }

    @Test
    void getUserProfile_notFound_throwsException() {
        // given - 존재하지 않는 사용자
        given(accountRepository.findByAccountId("nonExistent"))
                .willReturn(Optional.empty());

        // when & then - 예외 발생 검증
        assertThatThrownBy(() -> accountService.getUserProfile(Long.valueOf("nonExistent")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findByUsername() {
//        // given - 사용자 이름으로 검색 가능한 계정
//        given(accountRepository.findByUsername("testerName1"))
//                .willReturn(Optional.of(validAccount));
//
//        // when - 사용자 이름으로 조회
//        Optional<Account> result = accountService.findByUsername("testerName1");
//
//        // then - 조회 결과 검증
//        assertThat(result).isPresent();
//        assertThat(result.get().getUsername()).isEqualTo("testerName1");
//        verify(accountRepository, times(1)).findByUsername("testerName1");
    }

    @Test
    void findByEmail() {
//        // given - 이메일로 검색 가능한 계정
//        given(accountRepository.findByEmail("test@example.com"))
//                .willReturn(Optional.of(validAccount));
//
//        // when - 이메일로 조회
//        Optional<Account> result = accountService.findByEmail("test@example.com");
//
//        // then - 조회 결과 검증
//        assertThat(result).isPresent();
//        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
//        verify(accountRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void join() {
        // 이 테스트는 join_validAccount_success와 동일한 내용
        // 필요에 따라 다른 시나리오 테스트 가능
        // given
        given(accountRepository.existsByAccountId(validAccount.getAccountId())).willReturn(false);
        given(accountRepository.existsByEmail(validAccount.getEmail())).willReturn(false);
        given(accountRepository.save(any(LocalAccount.class))).willReturn(validAccount);

        // when
        Account result = accountService.join(validAccount);

        // then
        assertThat(result).isNotNull();
        verify(accountRepository).save(any(LocalAccount.class));
    }
}