package com.unibuc.vulnerableAPI.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private String message;
    private Boolean authorized;
}
