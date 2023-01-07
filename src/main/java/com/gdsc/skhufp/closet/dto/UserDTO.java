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
public class UserDTO {
    private long id;
    private String username;
    private String password;

    private LocalDateTime created_date;
    private LocalDateTime modified_date;

}

