package com.Springboot.SpringbootApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProductInventoryRequestDto {
    private String name;
    private Integer price;
    private Integer quantity;
    private String description;
    private Integer category;
}
