package com.Springboot.SpringbootApp.controller;


import com.Springboot.SpringbootApp.dto.AddProductInventoryRequestDto;
import com.Springboot.SpringbootApp.dto.ProductInventoryDto;
import com.Springboot.SpringbootApp.dto.UpdateProductInventoryRequestDto;
import com.Springboot.SpringbootApp.entity.ProductInventory;
import com.Springboot.SpringbootApp.mapper.impl.ProductInventoryMapper;
import com.Springboot.SpringbootApp.service.ProductInventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductInventoryController {

    ProductInventoryService service;
    ProductInventoryMapper mapper;

    public ProductInventoryController(ProductInventoryService service, ProductInventoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/products_inventory/{id}")
    public ProductInventoryDto get(@PathVariable(name = "id") Integer id){
        ProductInventory productInventory=service.getProductInventory(id);
        ProductInventoryDto responseDto=mapper.mapTo(productInventory);

        return  responseDto;

    }

    @DeleteMapping(path = "/products_inventory/{id}")
    public void delete(@PathVariable(name = "id") Integer id){
        service.deleteInventory(id);
    }

    @PostMapping(path = "/products_inventory")
    public ProductInventoryDto add(@RequestBody AddProductInventoryRequestDto requestDto){
        ProductInventory productInventory=service.addProductInventory(requestDto);
        ProductInventoryDto response=mapper.mapTo(productInventory);

        return response;
    }

    @PatchMapping(path = "/products_inventory/{id}")
    public ProductInventoryDto update(@PathVariable(name = "id") Integer id,
                                      @RequestBody UpdateProductInventoryRequestDto requestDto){
        ProductInventory productInventory=service.updateProductInventory(id,requestDto);
        ProductInventoryDto response=mapper.mapTo(productInventory);

        return response;
    }

}
