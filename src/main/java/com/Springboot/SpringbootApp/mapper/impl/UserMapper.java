package com.Springboot.SpringbootApp.mapper.impl;

import com.Springboot.SpringbootApp.dto.UserDto;
import com.Springboot.SpringbootApp.entity.User;
import com.Springboot.SpringbootApp.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User , UserDto> {
    ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDto mapTo(User user) {
        return modelMapper.map(user , UserDto.class);
    }

    @Override
    public User mapFrom(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }
}
