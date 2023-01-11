package com.gdsc.skhufp.common.exception.auth;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class NoPermissionException extends CustomAbstractException {
    public NoPermissionException() {
        super(StatusEnum.NO_PERMISSION);
    }
}
