package com.gdsc.skhufp.closet.dto.response;


import com.gdsc.skhufp.closet.domain.entity.ClothType;
import com.gdsc.skhufp.closet.domain.entity.Season;
import lombok.Builder;


import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ClothResponse (
        Long id,
        String imageUrl,
        ClothType type,
        Set<Season> seasons,
        String name,
        String comment,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) { }
