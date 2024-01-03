package com.Springboot.SpringbootApp.controller;

import com.Springboot.SpringbootApp.dto.AddCategoryRequestDto;
import com.Springboot.SpringbootApp.dto.CategoryDto;
import com.Springboot.SpringbootApp.dto.UpdateCategoryRequestDto;
import com.Springboot.SpringbootApp.entity.Category;
import com.Springboot.SpringbootApp.mapper.impl.CategoryMapper;
import com.Springboot.SpringbootApp.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    CategoryService categoryService;
    CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService,CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper=categoryMapper;
    }

    @PostMapping(path = "/categories")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryDto add(@RequestBody AddCategoryRequestDto requestDto){
        Category category= categoryService.addCategory(requestDto);
        CategoryDto responseDto=categoryMapper.mapTo(category);
        return responseDto;
    }

    @GetMapping(path = "/categories")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CategoryDto> getAll(){
        List<Category> categories=categoryService.getAllCategory();
        List<CategoryDto> responseDto=categories.stream()
                .map(category -> categoryMapper.mapTo(category))
                .collect(Collectors.toList());
        return responseDto;
    }

    @DeleteMapping(path = "/categories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable(name = "id") Integer id){
        categoryService.deleteCategory(id);
    }

    @GetMapping(path = "/categories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryDto get(@PathVariable(name = "id") Integer id){
        Category category=categoryService.getCategory(id);
        CategoryDto responseDto=categoryMapper.mapTo(category);;
        return responseDto;

    }

    @PatchMapping(path = "/categories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryDto update(@PathVariable(name = "id") Integer id,
                              @RequestBody UpdateCategoryRequestDto dto){
        Category category=categoryService.updateCategory(id,dto);
        CategoryDto responseDto=categoryMapper.mapTo(category);
        return responseDto;
    }
}
