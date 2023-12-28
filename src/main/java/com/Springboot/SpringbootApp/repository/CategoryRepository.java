package com.Springboot.SpringbootApp.repository;

import com.Springboot.SpringbootApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String category);
}
