package com.Springboot.SpringbootApp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInventoryDto {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private CategoryDto category;
    private Integer quantity;
}
