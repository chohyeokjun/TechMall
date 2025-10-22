package tech.technomart.common.enums;

import org.springframework.http.HttpStatus;

public enum SuccessCode implements BaseCode{

    // Auth
    SUCCESS_MEMBER_LOGIN(HttpStatus.OK, "로그인을 성공하였습니다."),
    SUCCESS_MEMBER_LOGOUT(HttpStatus.OK, "로그아웃 되었습니다."),
    SUCCESS_MEMBER_SIGNUP(HttpStatus.CREATED, "회원가입을 성공하였습니다."),
    SUCCESS_REISSUE_TOKEN(HttpStatus.OK, "토큰을 재발행합니다."),

    // Member
    SUCCESS_FIND_MEMBER(HttpStatus.OK, "마이페이지 조회를 성공하였습니다."),
    SUCCESS_UPDATE_MEMBER(HttpStatus.OK, "정보 수정을 성공하였습니다."),
    SUCCESS_UPDATE_PASSWORD(HttpStatus.OK, "비밀번호 수정을 성공하였습니다."),
    SUCCESS_DELETE_MEMBER(HttpStatus.OK, "회원 탈퇴가 완료되었습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    SuccessCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
