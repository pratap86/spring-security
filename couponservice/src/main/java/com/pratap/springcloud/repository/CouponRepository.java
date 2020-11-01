package com.pratap.springcloud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratap.springcloud.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

	Optional<CouponEntity> findByCode(String code);
	
}
