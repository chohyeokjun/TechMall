package tech.technomart.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import tech.technomart.domain.member.entity.Member;
import tech.technomart.domain.member.enums.MemberRole;

@Getter
@AllArgsConstructor
@Builder
public class SignupResponseDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private MemberRole memberRole;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public static SignupResponseDto of(Member member) {
        return SignupResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .memberRole(member.getMemberRole())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
