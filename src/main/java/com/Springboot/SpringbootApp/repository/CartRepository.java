package com.Springboot.SpringbootApp.repository;

import com.Springboot.SpringbootApp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart , Integer> {
}
