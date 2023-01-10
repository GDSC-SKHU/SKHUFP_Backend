package com.gdsc.skhufp.storage.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ImageFileDTO(
        Long id,
        String originalName,
        String convertedName,
        String s3Url,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) { }
