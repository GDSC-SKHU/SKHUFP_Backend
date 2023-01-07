package com.gdsc.skhufp.closet.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;


import java.time.LocalDateTime;

@Getter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class ClothDTO {
    private long id;
    private String image_url;
    private int type;
    private String comment;
    private String name;

    private LocalDateTime created_date;

    private LocalDateTime modified_date;

}
