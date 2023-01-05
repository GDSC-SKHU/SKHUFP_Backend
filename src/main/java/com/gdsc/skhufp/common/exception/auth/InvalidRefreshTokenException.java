package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.response.StatusEnum;
import com.gdsc.skhufp.common.exception.CustomAbstractException;

public class InvalidRefreshTokenException extends CustomAbstractException {
    public InvalidRefreshTokenException() {
        super(StatusEnum.INVALID_REFRESH_TOKEN);
    }
}
