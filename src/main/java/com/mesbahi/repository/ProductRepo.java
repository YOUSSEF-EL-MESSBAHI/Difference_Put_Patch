package com.mesbahi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mesbahi.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Integer> {

}
