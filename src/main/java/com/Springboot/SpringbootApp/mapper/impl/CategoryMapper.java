package com.Springboot.SpringbootApp.mapper.impl;


import com.Springboot.SpringbootApp.dto.CategoryDto;
import com.Springboot.SpringbootApp.entity.Category;
import com.Springboot.SpringbootApp.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category , CategoryDto> {
    ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto mapTo(Category category) {
       return modelMapper.map(category , CategoryDto.class);
    }

    @Override
    public Category mapFrom(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto , Category.class);
    }
}
