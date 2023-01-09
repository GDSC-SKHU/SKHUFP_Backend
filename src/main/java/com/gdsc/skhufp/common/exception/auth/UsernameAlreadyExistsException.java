package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class UsernameAlreadyExistsException extends CustomAbstractException {
    public UsernameAlreadyExistsException() {
        super(StatusEnum.USERNAME_ALREADY_EXISTS);
    }
}
