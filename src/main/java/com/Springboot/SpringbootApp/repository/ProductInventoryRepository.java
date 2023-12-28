package com.Springboot.SpringbootApp.repository;

import com.Springboot.SpringbootApp.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {
}
