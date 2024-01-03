package com.Springboot.SpringbootApp.service;

import com.Springboot.SpringbootApp.entity.Role;
import com.Springboot.SpringbootApp.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getRole(Integer id){
       Optional<Role> role= repository.findById(id);

       return role.orElseThrow(()-> new RuntimeException("role not found."));
    }
    public Role getRole(String rolename){
        Optional<Role> role= repository.findByRoleName(rolename);

        return role.orElseThrow(()-> new RuntimeException("role not found."));
    }
    public Role addRole(Role role){
        Role savedrole=repository.save(role);
        System.out.println("role saved: " +savedrole);
        return savedrole;
    }


    public void addRoles(List<Role> roles) {

        roles.stream().forEach((role -> repository.save(role)));

    }
}
