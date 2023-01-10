package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class NoImageOnClothException extends CustomAbstractException {
    public NoImageOnClothException() {
        super(StatusEnum.NO_IMAGE_ON_CLOTH);
    }
}
