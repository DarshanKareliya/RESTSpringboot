package com.Springboot.SpringbootApp.controller;


import com.Springboot.SpringbootApp.dto.AddProductInventoryRequestDto;
import com.Springboot.SpringbootApp.dto.ProductInventoryDto;
import com.Springboot.SpringbootApp.dto.UpdateProductInventoryRequestDto;
import com.Springboot.SpringbootApp.entity.ProductInventory;
import com.Springboot.SpringbootApp.mapper.impl.ProductInventoryMapper;
import com.Springboot.SpringbootApp.service.ProductInventoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductInventoryController {

    ProductInventoryService service;
    ProductInventoryMapper mapper;

    public ProductInventoryController(ProductInventoryService service, ProductInventoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/productsInventories/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ProductInventoryDto get(@PathVariable(name = "id") Integer id){
        ProductInventory productInventory=service.getProductInventory(id);
        ProductInventoryDto responseDto=mapper.mapTo(productInventory);

        return  responseDto;

    }
    @GetMapping(path = "/productsInventories")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<ProductInventoryDto> getAll(){
        List<ProductInventory> productInventories=service.getAllProductInventory();
        List<ProductInventoryDto> responseDto=productInventories.stream()
                .map(productInventory -> mapper.mapTo(productInventory))
                .collect(Collectors.toList());

        return  responseDto;

    }

    @DeleteMapping(path = "/productsInventories/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void delete(@PathVariable(name = "id") Integer id){
        service.deleteInventory(id);
    }

    @PostMapping(path = "/productsInventories")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ProductInventoryDto add(@RequestBody AddProductInventoryRequestDto requestDto){
        ProductInventory productInventory=service.addProductInventory(requestDto);
        ProductInventoryDto response=mapper.mapTo(productInventory);

        return response;
    }

    @PatchMapping(path = "/productsInventories/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ProductInventoryDto update(@PathVariable(name = "id") Integer id,
                                      @RequestBody UpdateProductInventoryRequestDto requestDto){
        ProductInventory productInventory=service.updateProductInventory(id,requestDto);
        ProductInventoryDto response=mapper.mapTo(productInventory);

        return response;
    }

}
