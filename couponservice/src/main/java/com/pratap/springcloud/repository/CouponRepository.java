package com.pratap.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratap.springcloud.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

	CouponEntity findByCode(String code);
}
