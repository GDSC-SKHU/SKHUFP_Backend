package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class LimitedNumberOfClothTypeException extends CustomAbstractException {
    public LimitedNumberOfClothTypeException() {
        super(StatusEnum.LIMITED_NUMBER_OF_CLOTH_TYPE);
    }
}
