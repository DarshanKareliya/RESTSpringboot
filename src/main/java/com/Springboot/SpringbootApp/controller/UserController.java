package com.Springboot.SpringbootApp.controller;

import com.Springboot.SpringbootApp.auth.JWTService;
import com.Springboot.SpringbootApp.dto.AddUserRequestDto;
import com.Springboot.SpringbootApp.dto.UpdateUserRequestDto;
import com.Springboot.SpringbootApp.dto.UserDto;
import com.Springboot.SpringbootApp.entity.User;
import com.Springboot.SpringbootApp.mapper.impl.UserMapper;
import com.Springboot.SpringbootApp.service.TokenService;
import com.Springboot.SpringbootApp.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class UserController {

    UserService userService;
    UserMapper userMapper;
    @Autowired
    TokenService tokenService;
    @Autowired
    JWTService jwtService;

    public UserController(UserService userService,UserMapper userMapper) {
        this.userService = userService;
        this.userMapper=userMapper;
    }

    @GetMapping(path = "/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")// for admin
    public UserDto get(@PathVariable(name = "id") Integer id){
        User user=userService.getUser(id);
        UserDto responseDto=userMapper.mapTo(user);
        return responseDto;
    }

    @GetMapping(path = "/users")
    @PreAuthorize("hasAuthority('USER')")// for user
    public UserDto get(@RequestHeader("Authorization") String token){
        log.info("inside get of user");

        String username= tokenService.usernameFromToken(token);
        log.info("got username from token");
        log.info("username is : "+ username);
        User user=userService.getUser(username);
        log.info("got user for username");
        UserDto responseDto=userMapper.mapTo(user);
        return responseDto;

    }

    @DeleteMapping(path = "/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")// for admin
    public void delete(@PathVariable(name = "id") Integer id)
    {
        userService.deleteUser(id);
    }

    @DeleteMapping(path = "/users")
    @PreAuthorize("hasAuthority('USER')")// for users
    public void delete(@RequestHeader("Authorization") String token)
    {
        String username= tokenService.usernameFromToken(token);
        User user=userService.getUser(username);
        userService.deleteUser(user.getId());
    }

    @PostMapping(path = "/users")   // for all
    public UserDto add(@RequestBody AddUserRequestDto dto)
    {
        User user=userService.addUser(dto);
        UserDto responseDto=userMapper.mapTo(user);
        return responseDto;
    }

    @PatchMapping(path = "/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")// for admin
    public UserDto update(@PathVariable(name = "id") Integer id,
                          @RequestBody UpdateUserRequestDto dto){
        User user=userService.updateUser(id,dto);
        UserDto responseDto=userMapper.mapTo(user);
        return responseDto;
    }

    @PatchMapping(path = "/users")
    @PreAuthorize("hasAuthority('USER')")// access url for users
    public UserDto update(@RequestHeader("Authorization") String token,
                          @RequestBody UpdateUserRequestDto dto){
        String username= tokenService.usernameFromToken(token);
        User user=userService.getUser(username);
        User updatedUser=userService.updateUser(user.getId(), dto);
        String jwtToken = jwtService.generateToken(updatedUser);
        UserDto responseDto=userMapper.mapTo(updatedUser);
        responseDto.setToken(jwtToken);
        return responseDto;
    }
}
