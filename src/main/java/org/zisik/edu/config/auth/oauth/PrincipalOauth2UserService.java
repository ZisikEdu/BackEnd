package org.zisik.edu.config.auth.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zisik.edu.config.auth.PrincipalDetails;
import org.zisik.edu.config.auth.oauth.provider.GoogleUserInfo;
import org.zisik.edu.config.auth.oauth.provider.NaverUserInfo;
import org.zisik.edu.config.auth.oauth.provider.OAuth2UserInfo;
import org.zisik.edu.user.domain.OAuthAccount;
import org.zisik.edu.user.domain.Role;
import org.zisik.edu.user.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;

    // 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료시 @AuthenticationPrincipal 어노테이션 만들어진다
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        //구글 로그인 버튼 클릭 -> 로그인 창 -> 로그인 완료 -> code를 리턴 (OAuth-client라이브러리) -> Access Token요청
        //UserRequest 정보-> loadUser함수 호출-> 구글로부터 회원 프로필 받아준다
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }

        assert oAuth2UserInfo != null;

        String provider = oAuth2UserInfo.getProvider(); //google
        String providerUserid = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerUserid; //google_123451687545465
        String password = passwordEncoder.encode(username);
        String email = oAuth2UserInfo.getEmail();
        Role role = Role.ROLE_USER;

        OAuthAccount userEntity = (OAuthAccount) accountRepository.findByUsername(username);

        if(userEntity == null) {
            System.out.println("구글 로그인 최초 입니다.");
            userEntity = OAuthAccount.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .providerUserid(providerUserid)
                    .provider(provider)
                    .role(role)
                    .build();

            accountRepository.save(userEntity);
        }
        else {
            System.out.println("이미 구글 계정으로 회원가입 실시 완료");
        }

        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
    }
}
