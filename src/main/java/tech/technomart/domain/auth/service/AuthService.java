package tech.technomart.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.technomart.common.config.PasswordEncoder;
import tech.technomart.common.enums.ErrorCode;
import tech.technomart.common.exception.BaseException;
import tech.technomart.domain.auth.dto.request.LoginRequestDto;
import tech.technomart.domain.auth.dto.response.LoginResponseDto;
import tech.technomart.domain.member.entity.Member;
import tech.technomart.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // member 찾기
        Member member = memberService.findMemberByEmail(loginRequestDto.getEmail());

        // 비밀번호 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD);
        }

        return LoginResponseDto.of(member);
    }

    // 로그아웃
    @Transactional
    public void logout() {
        // member 찾기

        // 로그아웃 처리
    }
}
