package com.Springboot.SpringbootApp.controller;

import com.Springboot.SpringbootApp.dto.AddRoleDto;
import com.Springboot.SpringbootApp.dto.RoleDto;
import com.Springboot.SpringbootApp.dto.UpdateRoleDto;
import com.Springboot.SpringbootApp.entity.Role;
import com.Springboot.SpringbootApp.mapper.impl.RoleMapper;
import com.Springboot.SpringbootApp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    RoleMapper roleMapper;

    @PostMapping(path = "/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RoleDto add(@RequestBody AddRoleDto dto){
        Role newRole=roleService.addRole(dto);
        RoleDto responseDto=roleMapper.mapTo(newRole);
        return  responseDto;
    }

    @GetMapping(path = "/roles/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RoleDto get(@PathVariable(name = "id") Integer id){
        Role role=roleService.getRole(id);
        RoleDto responseDto=roleMapper.mapTo(role);
        return responseDto;
    }

    @GetMapping(path = "/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RoleDto> getAll(){
        List<Role> roles=roleService.getAllRole();
        List<RoleDto> responseDto=roles.stream()
                .map(role -> roleMapper.mapTo(role))
                .collect(Collectors.toList());
        return responseDto;
    }

    @PatchMapping(path = "/roles/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RoleDto update(@PathVariable(name = "id")Integer id,
                          @RequestBody UpdateRoleDto dto){
        Role updatedRole=roleService.updateRole(id,dto);
        RoleDto responseDto=roleMapper.mapTo(updatedRole);
        return responseDto;
    }

    @DeleteMapping(path = "/roles/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable(name = "id") Integer id){
        roleService.deleteRole(id);
    }


}
