package com.Springboot.SpringbootApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCartRequestDto {
    private UserDto customer;
    private List<ProductDto> products;
}
