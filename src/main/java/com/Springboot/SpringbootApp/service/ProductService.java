package com.Springboot.SpringbootApp.service;


import com.Springboot.SpringbootApp.dto.AddProductRequestDto;
import com.Springboot.SpringbootApp.dto.ProductDto;
import com.Springboot.SpringbootApp.dto.UpdateProductRequestDto;
import com.Springboot.SpringbootApp.entity.Product;
import com.Springboot.SpringbootApp.mapper.impl.CategoryMapper;
import com.Springboot.SpringbootApp.repository.ProductRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Log
public class ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;

    CategoryMapper categoryMapper;

    public ProductService(ProductRepository productRepository, CategoryService categoryService,CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryMapper=categoryMapper;
    }


    public Product getProduct(Integer id) {
        Optional<Product> product=productRepository.findById(id);
       return product.orElse(null);
    }

    public Product addProduct(AddProductRequestDto dto) {
        Product newProduct=Product.builder()
                .description(dto.getDescription())
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .category(categoryService.getCategory(dto.getCategory()))
                .build();

        return productRepository.save(newProduct);
    }

    public void deleteProduct(int id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
        }
    }


    public Product updateProduct(Integer id, UpdateProductRequestDto productDto) {
        Product product=this.getProduct(id);
        if (Objects.nonNull(productDto.getName())){
            product.setName(productDto.getName());
        }
        if (Objects.nonNull(productDto.getDescription())) {
            product.setDescription(productDto.getDescription());
        }
        if (Objects.nonNull(productDto.getPrice())){
            product.setPrice(productDto.getPrice());
        }
        if (Objects.nonNull(productDto.getQuantity())){
            product.setQuantity(productDto.getQuantity());
        }
        if (Objects.nonNull(productDto.getCategory())){
            product.setCategory(categoryService.getCategory(productDto.getCategory()));
        }

        productRepository.save(product);

        return product;

    }
}
