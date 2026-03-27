package com.itability.memberservice.oauth2;

import com.itability.memberservice.jwt.JWTUtil;
import com.itability.memberservice.member.dao.MemberInfoRepo;
import com.itability.memberservice.member.dto.MemberInfoDTO;
import com.itability.memberservice.oauth2.dto.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final MemberInfoRepo memberInfoRepository;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Autowired
    public CustomSuccessHandler(JWTUtil jwtUtil, MemberInfoRepo memberInfoRepository) {
        this.jwtUtil = jwtUtil;
        this.memberInfoRepository = memberInfoRepository;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        // 사용자 정보 추출
        String username = customUserDetails.getUsername();
        String role = customUserDetails.getAuthorities().iterator().next().getAuthority(); // Simplified role retrieval

        // JWT 토큰 생성
        String token = jwtUtil.createJwt(username, role, 60 * 60 * 60L);

        // MemberInfoDTO 업데이트 (가정)
        MemberInfoDTO memberInfo = memberInfoRepository.findByUsername(username);
        if (memberInfo != null) {
            memberInfo.setToken(token); // 토큰 저장
            memberInfoRepository.save(memberInfo); // 변경사항 저장
        }
//
//        // 쿠키에 사용자 정보 넣기
//        Cookie usernameCookie = createCookie("username", username);
//        response.addCookie(usernameCookie);

        // JWT 토큰을 쿠키에 저장
        Cookie tokenCookie = createCookie("Authorization", token);
        response.addCookie(tokenCookie);

        // 리다이렉트
        response.sendRedirect(frontendUrl); // 프론트엔드 URL에 따라 조정
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
//        cookie.setHttpOnly(false);

        return cookie;
    }
}