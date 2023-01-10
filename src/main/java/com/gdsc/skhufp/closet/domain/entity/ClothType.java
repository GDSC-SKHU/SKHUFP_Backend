package com.gdsc.skhufp.closet.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ClothType {
    TOPS, BOTTOMS, OUTERWEAR, SHOES, BAGS, ETC;

    @JsonCreator
    public static ClothType from(String s) {
        return ClothType.valueOf(s.toUpperCase());
    }
}
