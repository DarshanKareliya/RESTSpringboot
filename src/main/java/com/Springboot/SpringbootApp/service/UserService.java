package com.Springboot.SpringbootApp.service;

import com.Springboot.SpringbootApp.dto.AddUserRequestDto;
import com.Springboot.SpringbootApp.dto.UpdateUserRequestDto;
import com.Springboot.SpringbootApp.entity.User;
import com.Springboot.SpringbootApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()
                        ->new RuntimeException("user not found"));
    }

    public void deleteUser(Integer id) {
        User user=this.getUser(id);
        userRepository.delete(user);
    }

    public User addUser(AddUserRequestDto dto) {
        User newUser =User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .role(dto.getRole())
                .mobile(dto.getMobile())
                .password(dto.getPassword())
                .build();
        return userRepository.save(newUser);
    }

    public User updateUser(Integer id, UpdateUserRequestDto dto) {
        User user=this.getUser(id);
        if (Objects.nonNull(dto.getUsername())){
            user.setUsername(dto.getUsername());
        }
        if (Objects.nonNull(dto.getEmail())){
            user.setEmail(dto.getEmail());
        }
        if (Objects.nonNull(dto.getRole())){
            user.setRole(dto.getRole());
        }
        if (Objects.nonNull(dto.getPassword())){
            user.setPassword(dto.getPassword());
        }
        if (Objects.nonNull(dto.getMobile())){
            user.setMobile(dto.getMobile());
        }

        return userRepository.save(user);

    }
}
