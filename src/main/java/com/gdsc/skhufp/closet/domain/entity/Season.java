package com.gdsc.skhufp.closet.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Season {
    SPRING, SUMMER, FALL, WINTER;

    @JsonCreator
    public static Season from(String s) {
        return Season.valueOf(s.toUpperCase());
    }
}
