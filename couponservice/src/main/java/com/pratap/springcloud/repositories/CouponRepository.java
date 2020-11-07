package com.pratap.springcloud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratap.springcloud.entities.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

	Optional<CouponEntity> findByCode(String code);
	
}
