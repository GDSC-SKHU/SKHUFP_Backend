package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class NotExpiredAccessTokenException extends CustomAbstractException {
    public NotExpiredAccessTokenException() {
        super(StatusEnum.NOT_EXPIRED_ACCESS_TOKEN);
    }
}
