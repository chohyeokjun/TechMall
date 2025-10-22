package tech.technomart.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.technomart.common.dto.CommonResponse;
import tech.technomart.common.enums.SuccessCode;
import tech.technomart.domain.auth.dto.request.LoginRequestDto;
import tech.technomart.domain.auth.dto.response.LoginResponseDto;
import tech.technomart.domain.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponseDto>> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        LoginResponseDto responseDto = authService.login(loginRequestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of(SuccessCode.SUCCESS_MEMBER_LOGIN, responseDto));
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<CommonResponse<Void>> logout() {
        authService.logout();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of(SuccessCode.SUCCESS_MEMBER_LOGOUT));
    }


}
