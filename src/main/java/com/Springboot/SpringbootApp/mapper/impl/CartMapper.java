package com.Springboot.SpringbootApp.mapper.impl;

import com.Springboot.SpringbootApp.dto.CartDto;
import com.Springboot.SpringbootApp.entity.Cart;
import com.Springboot.SpringbootApp.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapper implements Mapper<Cart, CartDto> {
    ModelMapper modelMapper;

    public CartMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CartDto mapTo(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public Cart mapFrom(CartDto cartDto) {
        return modelMapper.map(cartDto , Cart.class);
    }
}
