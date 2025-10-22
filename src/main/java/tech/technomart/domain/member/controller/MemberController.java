package tech.technomart.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.technomart.common.dto.CommonResponse;
import tech.technomart.common.enums.SuccessCode;
import tech.technomart.domain.member.dto.request.DeleteRequestDto;
import tech.technomart.domain.member.dto.request.SignupRequestDto;
import tech.technomart.domain.member.dto.response.SignupResponseDto;
import tech.technomart.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<SignupResponseDto>> signup(
            @Valid @RequestBody SignupRequestDto signupRequestDto
    ) {
        SignupResponseDto responseDto = memberService.signup(signupRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.of(SuccessCode.SUCCESS_MEMBER_SIGNUP, responseDto));
    }

    // 회원탈퇴
    @DeleteMapping("/{memberId}")
    public ResponseEntity<CommonResponse<Void>> deleteMember(
            @PathVariable Long memberId,
            @RequestBody DeleteRequestDto dto
    ) {
        memberService.delete(memberId, dto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of(SuccessCode.SUCCESS_DELETE_MEMBER));
    }

}
