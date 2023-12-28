package com.Springboot.SpringbootApp.service;

import com.Springboot.SpringbootApp.dto.AddCategoryRequestDto;
import com.Springboot.SpringbootApp.dto.CategoryDto;
import com.Springboot.SpringbootApp.dto.UpdateCategoryRequestDto;
import com.Springboot.SpringbootApp.entity.Category;
import com.Springboot.SpringbootApp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory(Integer categoryId) {
        Optional<Category> category1=categoryRepository.findById(categoryId);
        return category1.orElse(null);
    }

    public Category getCategoryByName(String category) {
        Optional<Category> category1=categoryRepository.findByName(category);
        return category1.orElse(null);
    }

    public void deleteCategory(Integer id) {
        Category category=this.getCategory(id);
        categoryRepository.delete(category);
    }

    public Category addCategory(AddCategoryRequestDto dto) {
        Category newCategory=Category.builder()
                .name(dto.getName())
                .build();

        return categoryRepository.save(newCategory);

    }

    public Category updateCategory(Integer id,UpdateCategoryRequestDto dto) {
        Category category=this.getCategory(id);
        if (!(category.getName().isEmpty())){
            category.setName(dto.getName());
        }
        categoryRepository.save(category);

        return category;
    }
}
