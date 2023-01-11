package com.gdsc.skhufp.closet.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gdsc.skhufp.common.exception.closet.ClothTypeBodyNotMatchException;

public enum ClothType {
    TOPS, BOTTOMS, OUTERWEAR, SHOES, BAGS, ETC;

    @JsonCreator
    public static ClothType from(String s) {
        try {
            return ClothType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ClothTypeBodyNotMatchException();
        }
    }
}
