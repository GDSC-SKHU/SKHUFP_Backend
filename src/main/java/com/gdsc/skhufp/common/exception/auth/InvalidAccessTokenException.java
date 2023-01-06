package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.response.StatusEnum;
import com.gdsc.skhufp.common.exception.CustomAbstractException;

public class InvalidAccessTokenException extends CustomAbstractException {
    public InvalidAccessTokenException() {
        super(StatusEnum.INVALID_ACCESS_TOKEN);
    }
}
