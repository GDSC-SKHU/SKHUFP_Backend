package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class ClothTypeBodyNotMatchException extends CustomAbstractException {
    public ClothTypeBodyNotMatchException() {
        super(StatusEnum.CLOTH_TYPE_BODY_NOT_MATCH);
    }
}
