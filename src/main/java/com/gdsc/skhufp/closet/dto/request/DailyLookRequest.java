package com.gdsc.skhufp.closet.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record DailyLookRequest(
        String name,
        String comment,
        @JsonProperty("cloth_ids")
        List<Long> clothIds
) {
}
