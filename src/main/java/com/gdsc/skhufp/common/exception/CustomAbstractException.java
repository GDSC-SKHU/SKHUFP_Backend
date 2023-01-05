package com.gdsc.skhufp.common.exception;

import com.gdsc.skhufp.common.response.StatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class CustomAbstractException extends RuntimeException {

    protected final StatusEnum statusEnum;
}
