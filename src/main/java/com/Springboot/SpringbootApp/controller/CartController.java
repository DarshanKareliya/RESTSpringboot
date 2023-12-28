package com.Springboot.SpringbootApp.controller;


import com.Springboot.SpringbootApp.dto.AddCartRequestDto;
import com.Springboot.SpringbootApp.dto.CartDto;
import com.Springboot.SpringbootApp.dto.UpdateCartRequestDto;
import com.Springboot.SpringbootApp.entity.Cart;
import com.Springboot.SpringbootApp.mapper.impl.CartMapper;
import com.Springboot.SpringbootApp.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    CartService cartService;
    CartMapper cartMapper;

    public CartController(CartService cartService,CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper=cartMapper;
    }

    @GetMapping(path = "/carts/{id}")
    public CartDto get(@PathVariable(name = "id") Integer id){
        Cart cart=cartService.getCart(id);
        return cartMapper.mapTo(cart);

    }

    @DeleteMapping(path = "/carts/{id}")
    public void delete(@PathVariable(name = "id") Integer id){
        cartService.deleteCart(id);
    }

    @PostMapping(path = "/carts")
    public CartDto add(@RequestBody AddCartRequestDto dto){
        Cart newcart=cartService.addCart(dto);
        return cartMapper.mapTo(newcart);
    }

    @PatchMapping(path = "/carts/{id}")
    public CartDto update(@PathVariable(name = "id") Integer id,
                          @RequestBody UpdateCartRequestDto dto){
        Cart cart=cartService.updateCart(id,dto);
        return cartMapper.mapTo(cart);
    }
}
