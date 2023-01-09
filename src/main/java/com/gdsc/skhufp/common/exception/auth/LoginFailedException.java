package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class LoginFailedException extends CustomAbstractException {
    public LoginFailedException() {
        super(StatusEnum.LOGIN_FAILED);
    }
}
