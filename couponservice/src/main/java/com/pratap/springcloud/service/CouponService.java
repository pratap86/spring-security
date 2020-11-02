package com.pratap.springcloud.service;

import java.util.List;

import com.pratap.springcloud.shared.dto.CouponDTO;

public interface CouponService {

	CouponDTO create(CouponDTO couponDTO);
	
	CouponDTO getCoupon(String code);

	List<CouponDTO> getCoupons();
}
