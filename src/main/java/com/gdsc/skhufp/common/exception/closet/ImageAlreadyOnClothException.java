package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class ImageAlreadyOnClothException extends CustomAbstractException {
    public ImageAlreadyOnClothException() {
        super(StatusEnum.IMAGE_ALREADY_ON_CLOTH);
    }
}
