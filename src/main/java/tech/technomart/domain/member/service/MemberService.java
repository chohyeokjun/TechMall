package tech.technomart.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.technomart.common.config.PasswordEncoder;
import tech.technomart.common.enums.ErrorCode;
import tech.technomart.common.exception.BaseException;
import tech.technomart.domain.member.dto.request.DeleteRequestDto;
import tech.technomart.domain.member.dto.request.SignupRequestDto;
import tech.technomart.domain.member.dto.response.SignupResponseDto;
import tech.technomart.domain.member.entity.Member;
import tech.technomart.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        // 이메일 중복검사
        if (memberRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new BaseException(ErrorCode.ALREADY_EXIST_EMAIL);
        }
        // 비밀번호 인코딩
        String encoded = passwordEncoder.encode(signupRequestDto.getPassword());

        // requestDto 기반 멤버 객체 생성
        Member member = SignupRequestDto.create(signupRequestDto, encoded);

        // DB에 저장
        memberRepository.save(member);

        return SignupResponseDto.of(member);
    }

    // 회원탈퇴
    public void delete(Long memberId, DeleteRequestDto dto) {
        // 회원 찾기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

        // 비밀번호 검사
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD);
        }

        // 회원정보 삭제
        memberRepository.delete(member);
    }

    // member 찾기(예외처리)
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
    }


}
