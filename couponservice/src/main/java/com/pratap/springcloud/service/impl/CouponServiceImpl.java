package com.pratap.springcloud.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.springcloud.entity.CouponEntity;
import com.pratap.springcloud.repository.CouponRepository;
import com.pratap.springcloud.service.CouponService;
import com.pratap.springcloud.shared.dto.CouponDTO;

@Service
public class CouponServiceImpl implements CouponService {
	
	private ModelMapper modelMapper;
	
	@Autowired
	private CouponRepository couponRepository;

	@Override
	public CouponDTO create(CouponDTO couponDTO) {
		
		modelMapper = new ModelMapper();
		CouponEntity couponEntity = modelMapper.map(couponDTO, CouponEntity.class);
		CouponEntity savedCouponEntity = couponRepository.save(couponEntity);
		
		CouponDTO returnCouponDto = modelMapper.map(savedCouponEntity, CouponDTO.class);
		return returnCouponDto;
	}

	@Override
	public CouponDTO getCoupon(String code) {

		modelMapper = new ModelMapper();
		CouponEntity couponResp = couponRepository.findByCode(code);
		return modelMapper.map(couponResp, CouponDTO.class);
	}

	@Override
	public List<CouponDTO> getCoupons() {

		List<CouponEntity> couponEntities = couponRepository.findAll();
		
		modelMapper = new ModelMapper();
		
		return couponEntities.stream().map(couponEntity -> modelMapper.map(couponEntity, CouponDTO.class)).collect(Collectors.toList());
	}

}
