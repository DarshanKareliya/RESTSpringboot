package com.Springboot.SpringbootApp.controller;

import com.Springboot.SpringbootApp.dto.AddUserRequestDto;
import com.Springboot.SpringbootApp.dto.UpdateUserRequestDto;
import com.Springboot.SpringbootApp.dto.UserDto;
import com.Springboot.SpringbootApp.entity.User;
import com.Springboot.SpringbootApp.mapper.impl.UserMapper;
import com.Springboot.SpringbootApp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    UserService userService;
    UserMapper userMapper;

    public UserController(UserService userService,UserMapper userMapper) {
        this.userService = userService;
        this.userMapper=userMapper;
    }

    @GetMapping(path = "/users/{id}")
    public UserDto get(@PathVariable(name = "id") Integer id){
        User user=userService.getUser(id);
        UserDto responseDto=userMapper.mapTo(user);
        return responseDto;
    }

    @DeleteMapping(path = "/users/{id}")
    public void delete(@PathVariable(name = "id") Integer id)
    {
        userService.deleteUser(id);
    }

    @PostMapping(path = "/users")
    public UserDto add(@RequestBody AddUserRequestDto dto)
    {
        User user=userService.addUser(dto);
        UserDto responseDto=userMapper.mapTo(user);
        return responseDto;
    }

    @PatchMapping(path = "/users/{id}")
    public UserDto update(@PathVariable(name = "id") Integer id, @RequestBody UpdateUserRequestDto dto){
        User user=userService.updateUser(id,dto);
        UserDto responseDto=userMapper.mapTo(user);
        return responseDto;
    }
}
