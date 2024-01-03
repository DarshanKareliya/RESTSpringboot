package com.Springboot.SpringbootApp.controller;


import com.Springboot.SpringbootApp.dto.AddCartRequestDto;
import com.Springboot.SpringbootApp.dto.CartDto;
import com.Springboot.SpringbootApp.dto.UpdateCartRequestDto;
import com.Springboot.SpringbootApp.entity.Cart;
import com.Springboot.SpringbootApp.mapper.impl.CartMapper;
import com.Springboot.SpringbootApp.service.CartService;
import com.Springboot.SpringbootApp.service.TokenService;
import com.Springboot.SpringbootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartController {

    CartService cartService;
    CartMapper cartMapper;
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    public CartController(CartService cartService,CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper=cartMapper;
    }

    @PostMapping(path = "/carts")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CartDto add(@RequestBody AddCartRequestDto dto){
        Cart newcart=cartService.addCart(dto);
        return cartMapper.mapTo(newcart);
    }

    @PostMapping(path = "/user/{id}/cart")
    @PreAuthorize("hasAuthority('USER')")
    public CartDto add(@RequestHeader("Authorization") String token,
                       @RequestBody AddCartRequestDto dto){
        String username= tokenService.usernameFromToken(token);
        Integer userid=userService.getUser(username).getId();
        dto.setCustomer(userid);
        Cart newcart=cartService.addCart(dto);
        return cartMapper.mapTo(newcart);
    }

    @GetMapping(path = "/carts/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CartDto get(@PathVariable(name = "id") Integer id){
        Cart cart=cartService.getCart(id);
        return cartMapper.mapTo(cart);
    }
    @GetMapping(path = "/carts")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CartDto> getAll(){
        List<Cart> carts=cartService.getAllCart();
        return carts.stream()
                .map(cart -> cartMapper.mapTo(cart))
                .collect(Collectors.toList());
    }
    @GetMapping(path = "/user/{id}/cart")
    @PreAuthorize("hasAuthority('USER')")
    public CartDto get(@RequestHeader("Authorization") String token){
        String username= tokenService.usernameFromToken(token);
        Integer userid=userService.getUser(username).getId();
        Cart cart=cartService.getCartOfUser(userid);

        return cartMapper.mapTo(cart);
    }

    @PatchMapping(path = "/carts/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CartDto update(@PathVariable(name = "id") Integer id,
                          @RequestBody UpdateCartRequestDto dto){
        Cart cart=cartService.updateCart(id,dto);
        return cartMapper.mapTo(cart);
    }

    @PatchMapping(path = "/user/{id}/cart")
    @PreAuthorize("hasAuthority('USER')")
    public CartDto update(@RequestHeader("Authorization") String token,
                          @RequestBody UpdateCartRequestDto dto){
        String username= tokenService.usernameFromToken(token);
        Integer userid=userService.getUser(username).getId();
        Cart cart=cartService.updateCart(userid,dto);
        return cartMapper.mapTo(cart);
    }

    @DeleteMapping(path = "/carts/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable(name = "id") Integer id) {
        cartService.deleteCart(id);
    }
}
