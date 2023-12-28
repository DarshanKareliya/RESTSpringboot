package com.Springboot.SpringbootApp.repository;

import com.Springboot.SpringbootApp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Integer> {
}
