package com.Springboot.SpringbootApp.controller;


import com.Springboot.SpringbootApp.dto.AddProductRequestDto;
import com.Springboot.SpringbootApp.dto.ProductDto;
import com.Springboot.SpringbootApp.dto.UpdateProductRequestDto;
import com.Springboot.SpringbootApp.entity.Product;
import com.Springboot.SpringbootApp.mapper.impl.ProductMapper;
import com.Springboot.SpringbootApp.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@Log
public class ProductController {

    ProductService productService;
    ProductMapper productMapper;

    public ProductController(ProductService productService,ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper=productMapper;

    }

    @PostMapping(path = "/products")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductDto add(@RequestBody AddProductRequestDto addProductRequestDto){

        Product product=productService.addProduct(addProductRequestDto);

        ProductDto responseDto=productMapper.mapTo(product);

        return responseDto;
    }

    @GetMapping(path = "/products/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductDto get(@PathVariable(name = "id") Integer id){
        Product product=productService.getProduct(id);
        ProductDto responseDto=productMapper.mapTo(product);

        return responseDto;
    }
    @GetMapping(path = "/products")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ProductDto> getAll(){
        List<Product> products=productService.getAllProduct();
        List<ProductDto> responseDto=products.stream()
                .map(product -> productMapper.mapTo(product))
                .collect(Collectors.toList());
        return responseDto;
    }

    @PatchMapping(path = "/products/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductDto update(@PathVariable(name = "id") Integer id,
                             @RequestBody UpdateProductRequestDto dto){
        Product product=productService.updateProduct(id,dto);
        ProductDto responseDto=productMapper.mapTo(product);

        return responseDto;

    }

    @DeleteMapping(path = "/products/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable(name = "id") Integer id){

        productService.deleteProduct(id);
    }
}
