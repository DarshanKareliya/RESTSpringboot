package com.Springboot.SpringbootApp.controller;

import com.Springboot.SpringbootApp.dto.AddCategoryRequestDto;
import com.Springboot.SpringbootApp.dto.CategoryDto;
import com.Springboot.SpringbootApp.dto.UpdateCategoryRequestDto;
import com.Springboot.SpringbootApp.entity.Category;
import com.Springboot.SpringbootApp.mapper.impl.CategoryMapper;
import com.Springboot.SpringbootApp.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    CategoryService categoryService;
    CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService,CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper=categoryMapper;
    }

    @PostMapping(path = "/categories")
    public CategoryDto add(@RequestBody AddCategoryRequestDto requestDto){
        Category category= categoryService.addCategory(requestDto);
        CategoryDto responseDto=categoryMapper.mapTo(category);
        return responseDto;
    }

    @DeleteMapping(path = "/categories/{id}")
    public void delete(@PathVariable(name = "id") Integer id){
        categoryService.deleteCategory(id);
    }

    @GetMapping(path = "/categories/{id}")
    public CategoryDto get(@PathVariable(name = "id") Integer id){
        Category category=categoryService.getCategory(id);
        CategoryDto responseDto=categoryMapper.mapTo(category);;
        return responseDto;

    }

    @PatchMapping(path = "/categories/{id}")
    public CategoryDto update(@PathVariable(name = "id") Integer id,
                              @RequestBody UpdateCategoryRequestDto dto){
        Category category=categoryService.updateCategory(id,dto);
        CategoryDto responseDto=categoryMapper.mapTo(category);
        return responseDto;
    }
}
