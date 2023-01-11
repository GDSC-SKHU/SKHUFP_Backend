package com.gdsc.skhufp.common.exception.closet;

import com.gdsc.skhufp.common.exception.CustomAbstractException;
import com.gdsc.skhufp.common.response.StatusEnum;

public class SeasonTypeParamNotMatchException extends CustomAbstractException {
    public SeasonTypeParamNotMatchException() {
        super(StatusEnum.SEASON_PARAM_NOT_MATCH);
    }
}
