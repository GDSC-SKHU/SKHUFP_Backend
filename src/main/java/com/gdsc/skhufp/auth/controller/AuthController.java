package com.gdsc.skhufp.auth.controller;

import com.gdsc.skhufp.auth.dto.UserDTO;
import com.gdsc.skhufp.common.response.StatusEnum;
import com.gdsc.skhufp.auth.dto.TokenDTO;
import com.gdsc.skhufp.auth.service.AuthService;
import com.gdsc.skhufp.auth.util.HeaderUtil;
import com.gdsc.skhufp.common.response.SuccessResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<SuccessResponseBody<TokenDTO>> signIn(HttpServletResponse response, @RequestBody UserDTO userRequest) {
        TokenDTO tokenResponse = authService.signIn(userRequest);
        HeaderUtil.setRefreshToken(response, tokenResponse.refreshToken());

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_SIGN_IN, tokenResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SuccessResponseBody<TokenDTO>> signUp(HttpServletResponse response, @RequestBody UserDTO userRequest) {
        authService.signUp(userRequest);
        TokenDTO tokenResponse = authService.signIn(userRequest);
        HeaderUtil.setRefreshToken(response, tokenResponse.refreshToken());

        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_SIGN_UP, tokenResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<SuccessResponseBody<TokenDTO>> refresh(HttpServletRequest request) {
        TokenDTO tokenRequest = TokenDTO.builder()
                .accessToken(HeaderUtil.getAccessToken(request))
                .refreshToken(HeaderUtil.getRefreshToken(request))
                .build();
        
        TokenDTO newTokenResponse = authService.refresh(tokenRequest);
        
        return SuccessResponseBody.toResponseEntity(StatusEnum.SUCCESS_REFRESH, newTokenResponse);
    }
}
