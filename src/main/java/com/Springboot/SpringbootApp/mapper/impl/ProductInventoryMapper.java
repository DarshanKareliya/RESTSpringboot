package com.Springboot.SpringbootApp.mapper.impl;

import com.Springboot.SpringbootApp.dto.ProductInventoryDto;
import com.Springboot.SpringbootApp.entity.ProductInventory;
import com.Springboot.SpringbootApp.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductInventoryMapper implements Mapper<ProductInventory , ProductInventoryDto> {

    ModelMapper modelMapper;

    public ProductInventoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductInventoryDto mapTo(ProductInventory productInventory) {
        return modelMapper.map(productInventory, ProductInventoryDto.class);
    }

    @Override
    public ProductInventory mapFrom(ProductInventoryDto productInventoryDto) {
        return modelMapper.map(productInventoryDto, ProductInventory.class);
    }
}
