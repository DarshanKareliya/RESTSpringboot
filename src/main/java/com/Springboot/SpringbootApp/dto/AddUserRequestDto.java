package com.Springboot.SpringbootApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserRequestDto {
    private String username;
    private String email;
    private String password;
    private String role;
    private Long mobile;
}
