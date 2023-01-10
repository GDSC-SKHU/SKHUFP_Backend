package com.gdsc.skhufp.closet.dto;


import com.gdsc.skhufp.closet.domain.entity.ClothType;
import com.gdsc.skhufp.closet.domain.entity.Season;
import lombok.Builder;


import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ClothDTO (
        Long id,
        String imageUrl,
        ClothType type,
        Set<Season> seasons,
        String name,
        String comment,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) { }
