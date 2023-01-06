package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.response.StatusEnum;
import com.gdsc.skhufp.common.exception.CustomAbstractException;

public class RefreshTokenNotFoundException extends CustomAbstractException {
    public RefreshTokenNotFoundException() {
        super(StatusEnum.REFRESH_TOKEN_NOT_FOUND);
    }
}
