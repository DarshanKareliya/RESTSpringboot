package com.Springboot.SpringbootApp.repository;

import com.Springboot.SpringbootApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Integer> {
}
