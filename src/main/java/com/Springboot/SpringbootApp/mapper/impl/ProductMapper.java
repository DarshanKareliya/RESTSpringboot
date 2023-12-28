package com.Springboot.SpringbootApp.mapper.impl;

import com.Springboot.SpringbootApp.dto.ProductDto;
import com.Springboot.SpringbootApp.entity.Product;
import com.Springboot.SpringbootApp.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product , ProductDto> {

    ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto mapTo(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product mapFrom(ProductDto dto) {
        return modelMapper.map(dto, Product.class);
    }
}
