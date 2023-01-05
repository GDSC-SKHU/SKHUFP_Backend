package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.response.StatusEnum;
import com.gdsc.skhufp.common.exception.CustomAbstractException;

public class UserNotFoundException extends CustomAbstractException {
    public UserNotFoundException() {
        super(StatusEnum.USER_NOT_FOUND);
    }
}
