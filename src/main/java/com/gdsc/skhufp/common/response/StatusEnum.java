package com.gdsc.skhufp.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum StatusEnum {
    // 200
    SUCCESS_RETURN_DATA(OK, "데이터 반환 성공"),
    SUCCESS_UPDATE_DATA(OK, "데이터 업데이트 성공"),
    SUCCESS_DELETE_DATA(OK, "데이터 지우기 성공"),

    // 201
    SUCCESS_SIGN_IN(CREATED, "로그인 성공(Access token, Refresh token 발급 성공)"),
    SUCCESS_SIGN_UP(CREATED, "회원가입 성공(Access token, Refresh token 발급 성공)"),
    SUCCESS_REFRESH(CREATED, "Access token refresh 성공"),
    CREATED_CLOTH(CREATED, "옷 생성 성공"),
    CREATED_DAILY_LOOK(CREATED, "Daily Look 생성 성공"),

    // 204
    NO_DATA(NO_CONTENT, "데이터 없음"),

    // 400
    NOT_EXPIRED_ACCESS_TOKEN(BAD_REQUEST, "Access token이 만료되지 않았습니다"),
    USERNAME_ALREADY_EXISTS(BAD_REQUEST, "해당 username을 가진 user가 이미 존재합니다."),
    IMAGE_ALREADY_ON_CLOTH(BAD_REQUEST, "이미 해당 옷에는 이미지가 등록되어 있습니다."),
    NO_IMAGE_ON_CLOTH(BAD_REQUEST, "해당 옷에는 이미지가 등록되어 있지 않습니다."),
    CLOTH_TYPE_PARAM_NOT_MATCH(BAD_REQUEST, "Request Param을 확인 해주세요(사용 가능한 목록 - tops, bottoms, outerwear, shoes, bags, etc)"),
    SEASON_PARAM_NOT_MATCH(BAD_REQUEST, "Request Param을 확인 해주세요(사용 가능한 목록 - spring, summer, fall, winter)"),
    CLOTH_TYPE_BODY_NOT_MATCH(BAD_REQUEST, "Request Body에서 type을 확인 해주세요(사용 가능한 목록 - tops, bottoms, outerwear, shoes, bags, etc)"),
    SEASON_BODY_NOT_MATCH(BAD_REQUEST, "Request Body에서 season을 확인 해주세요(사용 가능한 목록 - spring, summer, fall, winter)"),
    LIMITED_NUMBER_OF_CLOTH_TYPE(BAD_REQUEST, "해당 타입(tops, bottoms, outerwear, shoes, bags)은 개수가 1개 이하여야 합니다."),

    // 401
    INVALID_ACCESS_TOKEN(UNAUTHORIZED, "Access token이 잘못되었습니다."),
    INVALID_REFRESH_TOKEN(UNAUTHORIZED, "Refresh token이 잘못되었습니다."),
    ACCESS_TOKEN_IS_NULL(UNAUTHORIZED, "Access_token이 존재하지 않습니다."),
    LOGIN_FAILED(UNAUTHORIZED, "유저 ID나 비밀번호가 틀립니다."),

    // 403
    EXPIRE_ACCESS_TOKEN(FORBIDDEN, "Access token이 만료되었습니다."),
    EXPIRE_REFRESH_TOKEN(FORBIDDEN, "Refresh token이 만료되었습니다."),
    NO_PERMISSION(FORBIDDEN, "요청한 사용자는 권한이 없습니다."),

    // 404
    USER_NOT_FOUND(NOT_FOUND, "유저를 찾을 수 없음"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "Refresh Token DB에 존재하지 않음"),
    IMAGE_FILE_NOT_FOUND(NOT_FOUND, "Image File DB에 존재하지 않음"),
    CLOTH_NOT_FOUND(NOT_FOUND, "옷 DB에 존재하지 않음"),
    DAILY_LOOK_NONT_FOUND(NOT_FOUND, "Daily Look DB에 존재하지 않음"),

    // 500
    COULD_NOT_SAVE_FILE_IN_S3(INTERNAL_SERVER_ERROR, "S3 저장소에 파일을 저장 할 수 없습니다");

    private final HttpStatus httpStatus;
    private String detail;

    StatusEnum(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    public StatusEnum setDetail(String detail) {
        this.detail = detail;
        return this;
    }
}
