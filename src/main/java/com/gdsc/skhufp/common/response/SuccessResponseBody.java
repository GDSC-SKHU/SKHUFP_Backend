package com.gdsc.skhufp.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class SuccessResponseBody<T> extends AbstractResponseBody {

    private final T data;

    @Builder
    public SuccessResponseBody(int status, String statusDetail, String code, String message,
                               T data) {
        super(status, statusDetail, code, message);
        this.data = data;
    }

    public static <T> ResponseEntity<SuccessResponseBody<T>> toResponseEntity(StatusEnum statusEnum, T data) {
        return ResponseEntity
                .status(statusEnum.getHttpStatus())
                .body(SuccessResponseBody.<T>builder()
                        .status(statusEnum.getHttpStatus()
                                .value())
                        .statusDetail(statusEnum.getHttpStatus()
                                .name())
                        .code(statusEnum.name())
                        .message(statusEnum.getDetail())
                        .data(data)
                        .build()
                );
    }

    public static <T> ResponseEntity<SuccessResponseBody<T>> toEmptyResponseEntity() {
        return ResponseEntity
                .status(StatusEnum.NO_DATA.getHttpStatus())
                .body(SuccessResponseBody.<T>builder()
                        .status(StatusEnum.NO_DATA.getHttpStatus().value())
                        .statusDetail(StatusEnum.NO_DATA.getHttpStatus().name())
                        .code(StatusEnum.NO_DATA.name())
                        .message(StatusEnum.NO_DATA.getDetail())
                        .data(null)
                        .build()
                );
    }
}

