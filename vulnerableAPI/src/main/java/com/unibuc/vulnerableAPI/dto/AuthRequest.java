package com.unibuc.vulnerableAPI.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequest {
    @NotBlank
    @Size(max=200)
    private String username;
    @NotBlank
    @Size(max=200)
    private String password;
}
