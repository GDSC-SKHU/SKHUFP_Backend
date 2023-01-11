package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class SeasonTypeBodyNotMatchException extends CustomAbstractException {
    public SeasonTypeBodyNotMatchException() {
        super(StatusEnum.SEASON_BODY_NOT_MATCH);
    }
}
