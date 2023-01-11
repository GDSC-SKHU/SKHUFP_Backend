package com.gdsc.skhufp.closet.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gdsc.skhufp.common.exception.closet.SeasonTypeBodyNotMatchException;

public enum Season {
    SPRING, SUMMER, FALL, WINTER;

    @JsonCreator
    public static Season from(String s) {
        try {
            return Season.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SeasonTypeBodyNotMatchException();
        }
    }
}
