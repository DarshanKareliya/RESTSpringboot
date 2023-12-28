package com.Springboot.SpringbootApp.controller;


import com.Springboot.SpringbootApp.dto.AddProductRequestDto;
import com.Springboot.SpringbootApp.dto.ProductDto;
import com.Springboot.SpringbootApp.dto.UpdateProductRequestDto;
import com.Springboot.SpringbootApp.entity.Product;
import com.Springboot.SpringbootApp.mapper.impl.ProductMapper;
import com.Springboot.SpringbootApp.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;



@RestController
@Log
public class ProductController {

    ProductService productService;
    ProductMapper productMapper;

    public ProductController(ProductService productService,ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper=productMapper;

    }

    @GetMapping(path = "/products/{id}")
    public ProductDto get(@PathVariable(name = "id") Integer id){
        Product product=productService.getProduct(id);
        ProductDto responseDto=productMapper.mapTo(product);

        return responseDto;
    }

    @PostMapping(path = "/products")
    public ProductDto add(@RequestBody AddProductRequestDto addProductRequestDto){

        Product product=productService.addProduct(addProductRequestDto);

        ProductDto responseDto=productMapper.mapTo(product);

        return responseDto;
    }


    @DeleteMapping(path = "/products/{id}")
    public void delete(@PathVariable(name = "id") Integer id){

        productService.deleteProduct(id);
    }

    @PatchMapping(path = "/products/{id}")
    public ProductDto update(@PathVariable(name = "id") Integer id,
                             @RequestBody UpdateProductRequestDto dto){

        Product product=productService.updateProduct(id,dto);

        ProductDto responseDto=productMapper.mapTo(product);

        return responseDto;

    }
}
