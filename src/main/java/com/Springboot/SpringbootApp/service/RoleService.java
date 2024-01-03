package com.Springboot.SpringbootApp.service;

import com.Springboot.SpringbootApp.dto.AddRoleDto;
import com.Springboot.SpringbootApp.dto.UpdateRoleDto;
import com.Springboot.SpringbootApp.entity.Role;
import com.Springboot.SpringbootApp.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public Role addRole(AddRoleDto dto){
        Role newRole=Role.builder()
                .roleName(dto.getName())
                .build();
        Role savedrole=repository.save(newRole);
        System.out.println("role saved: " +savedrole);
        return savedrole;
    }


    public void addRoles(List<Role> roles) {

        roles.stream().forEach((role -> repository.save(role)));

    }

    public List<Role> getAllRole() {
        return repository.findAll();
    }

    public Role updateRole(Integer id, UpdateRoleDto dto) {
        Role role=this.getRole(id);
        if (Objects.nonNull(dto.getName())){
            role.setRoleName(dto.getName());
        }
        repository.save(role);
        return role;
    }

    public void deleteRole(Integer id) {
        Role role=this.getRole(id);
        repository.delete(role);
    }
}
