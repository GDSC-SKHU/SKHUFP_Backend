package com.gdsc.skhufp.closet.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record DailyLookResponse(
        Long id,
        String name,
        String comment,
        ClothResponse tops,
        ClothResponse bottoms,
        ClothResponse outerwear,
        ClothResponse shoes,
        ClothResponse bags,
        List<ClothResponse> etc,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {
}
