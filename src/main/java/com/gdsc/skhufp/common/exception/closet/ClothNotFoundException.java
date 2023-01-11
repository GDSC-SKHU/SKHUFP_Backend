package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class ClothNotFoundException extends CustomAbstractException {

    public ClothNotFoundException() {
        super(StatusEnum.CLOTH_NOT_FOUND);
    }
}
