package com.pratap.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratap.springcloud.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
