package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.response.StatusEnum;
import com.gdsc.skhufp.common.exception.CustomAbstractException;

public class ExpiredRefreshTokenException extends CustomAbstractException {
    public ExpiredRefreshTokenException() {
        super(StatusEnum.EXPIRE_REFRESH_TOKEN);
    }
}
