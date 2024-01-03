package com.Springboot.SpringbootApp.repository;

import com.Springboot.SpringbootApp.entity.Cart;
import com.Springboot.SpringbootApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository  extends JpaRepository<Cart , Integer> {
    Optional<Cart> findByCustomer(User user);
}
