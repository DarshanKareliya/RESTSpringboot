package com.Springboot.SpringbootApp.mapper.impl;

import com.Springboot.SpringbootApp.dto.RoleDto;
import com.Springboot.SpringbootApp.entity.Role;
import com.Springboot.SpringbootApp.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<Role, RoleDto> {

    @Autowired
    ModelMapper modelMapper;
    @Override
    public RoleDto mapTo(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public Role mapFrom(RoleDto roleDto) {
        return modelMapper.map(roleDto,Role.class);
    }
}
