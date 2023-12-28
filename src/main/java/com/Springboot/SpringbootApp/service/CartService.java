package com.Springboot.SpringbootApp.service;

import com.Springboot.SpringbootApp.dto.AddCartRequestDto;
import com.Springboot.SpringbootApp.dto.ProductDto;
import com.Springboot.SpringbootApp.dto.UpdateCartRequestDto;
import com.Springboot.SpringbootApp.entity.Cart;
import com.Springboot.SpringbootApp.entity.Category;
import com.Springboot.SpringbootApp.entity.Product;
import com.Springboot.SpringbootApp.mapper.impl.ProductMapper;
import com.Springboot.SpringbootApp.mapper.impl.UserMapper;
import com.Springboot.SpringbootApp.repository.CartRepository;
import com.Springboot.SpringbootApp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class CartService {

    CartRepository cartRepository;
    UserMapper userMapper;
    ProductMapper productMapper;
    UserService userService;

    public CartService(CartRepository cartRepository,UserMapper userMapper,ProductMapper productMapper,UserService userService) {
        this.cartRepository = cartRepository;
        this.userMapper= userMapper;
        this.productMapper=productMapper;
        this.userService=userService;
    }

    public Cart getCart(Integer id) {
        Optional<Cart> cart=cartRepository.findById(id);
        return cart.orElse(null);
    }

    public void deleteCart(Integer id) {
        Cart cart=this.getCart(id);
        cartRepository.delete(cart);
    }

    public Cart addCart(AddCartRequestDto dto) {

        Cart newCart=Cart.builder()
                .customer(userService.getUser(dto.getCustomer().getId()))
                .products(dto.getProducts().stream()
                        .map((productDto)->
                        productMapper.mapFrom(productDto))
                        .collect(Collectors.toList()))
                .build();

        cartRepository.save(newCart);

        return newCart;
    }
    public Cart updateCart(Integer id,UpdateCartRequestDto dto) {

        Cart cart=this.getCart(id);
        if (Objects.nonNull(dto.getCustomer())){
            cart.setCustomer(userMapper.mapFrom(dto.getCustomer()));
        }
        if (Objects.nonNull(dto.getProducts())) {
            cart.setProducts(dto.getProducts()
                    .stream()
                    .map((productDto)->
                            productMapper.mapFrom(productDto))
                    .collect(Collectors.toList()));
        }
        cartRepository.save(cart);

        return cart;
    }
}
