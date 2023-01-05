package com.gdsc.skhufp.auth.dto;

import lombok.Builder;

@Builder
public record UserDTO(String username, String password) {
}
