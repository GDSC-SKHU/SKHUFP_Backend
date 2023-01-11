package com.gdsc.skhufp.closet.dto.response;


import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ClothResponse (
        Long id,
        String imageUrl,
        String type,
        List<String> seasons,
        String name,
        String comment,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) { }
