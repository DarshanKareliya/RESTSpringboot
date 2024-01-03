package com.Springboot.SpringbootApp.service;


import com.Springboot.SpringbootApp.dto.AddProductInventoryRequestDto;
import com.Springboot.SpringbootApp.dto.UpdateProductInventoryRequestDto;
import com.Springboot.SpringbootApp.entity.Product;
import com.Springboot.SpringbootApp.entity.ProductInventory;
import com.Springboot.SpringbootApp.repository.ProductInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductInventoryService {
    ProductInventoryRepository inventoryRepository;
    CategoryService categoryService;

    public ProductInventoryService(ProductInventoryRepository inventoryRepository,CategoryService categoryService) {
        this.inventoryRepository = inventoryRepository;
        this.categoryService=categoryService;
    }

    public ProductInventory getProductInventory(Integer id) {
        Optional<ProductInventory> productInventory=inventoryRepository.findById(id);
        return productInventory.orElseThrow(()->new RuntimeException("product inventory not found"));
    }


    public void deleteInventory(Integer id) {
        Optional<ProductInventory> productInventory=inventoryRepository.findById(id);
        if(productInventory.isPresent()){
            inventoryRepository.delete(productInventory.get());
        }
    }

    public ProductInventory addProductInventory(AddProductInventoryRequestDto requestDto) {
        ProductInventory newProduct=ProductInventory.builder()
                .description(requestDto.getDescription())
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .quantity(requestDto.getQuantity())
                .category(categoryService.getCategory(requestDto.getCategory()))
                .build();

        return inventoryRepository.save(newProduct);
    }


    public ProductInventory updateProductInventory(Integer id, UpdateProductInventoryRequestDto requestDto) {
        ProductInventory productInventory=this.getProductInventory(id);
        if (Objects.nonNull(requestDto.getName())){
            productInventory.setName(requestDto.getName());
        }
        if (Objects.nonNull(requestDto.getDescription())) {
            productInventory.setDescription(requestDto.getDescription());
        }
        if (Objects.nonNull(requestDto.getPrice())){
            productInventory.setPrice(requestDto.getPrice());
        }
        if (Objects.nonNull(requestDto.getQuantity())){
            productInventory.setQuantity(requestDto.getQuantity());
        }
        if (Objects.nonNull(requestDto.getCategory())){
            productInventory.setCategory(categoryService.getCategory(requestDto.getCategory()));
        }

        inventoryRepository.save(productInventory);

        return productInventory;
    }

    public List<ProductInventory> getAllProductInventory() {
        return inventoryRepository.findAll();
    }
}
