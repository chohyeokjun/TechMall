package tech.technomart.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import tech.technomart.domain.member.entity.Member;
import tech.technomart.domain.member.enums.MemberRole;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private MemberRole memberRole;

    public static LoginResponseDto of (Member member) {
        return LoginResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .memberRole(member.getMemberRole())
                .build();
    }
}
