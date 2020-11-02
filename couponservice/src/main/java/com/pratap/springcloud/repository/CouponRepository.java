package com.pratap.springcloud.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pratap.springcloud.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {

	Optional<CouponEntity> findByCode(String code);
	
	@Modifying(flushAutomatically = true)
	@Query("UPDATE Coupon set discount = :discount, expDate =: expDate where code =: code")
	CouponEntity updateCoupon(@Param(value = "discount") BigDecimal discount, @Param("expDate") String expDate, @Param("code") String code);
	
}
