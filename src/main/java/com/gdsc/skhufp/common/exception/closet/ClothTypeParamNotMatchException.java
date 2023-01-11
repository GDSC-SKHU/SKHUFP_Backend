package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class ClothTypeParamNotMatchException extends CustomAbstractException {
    public ClothTypeParamNotMatchException() {
        super(StatusEnum.CLOTH_TYPE_PARAM_NOT_MATCH);
    }
}
