package com.pratap.springcloud.service;

import java.util.List;

import com.pratap.springcloud.shared.dto.CouponDTO;
import com.pratap.springcloud.shared.dto.CouponUpdateDTO;

public interface CouponService {

	CouponDTO create(CouponDTO couponRequest);
	
	CouponDTO getCoupon(String code);

	List<CouponDTO> getCoupons();
	
	CouponDTO updateCoupon(String code, CouponUpdateDTO couponUpdateRequest);
}
