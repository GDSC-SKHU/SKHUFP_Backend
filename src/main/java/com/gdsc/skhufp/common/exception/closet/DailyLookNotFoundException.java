package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class DailyLookNotFoundException extends CustomAbstractException {
    public DailyLookNotFoundException() {
        super(StatusEnum.DAILY_LOOK_NONT_FOUND);
    }
}
