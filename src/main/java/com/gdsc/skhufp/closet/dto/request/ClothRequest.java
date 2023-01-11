package com.gdsc.skhufp.closet.dto.request;

import com.gdsc.skhufp.closet.domain.entity.ClothType;
import com.gdsc.skhufp.closet.domain.entity.Season;

import java.util.Set;

public record ClothRequest(
        ClothType type,
        Set<Season> seasons,
        String name,
        String comment
) { }
