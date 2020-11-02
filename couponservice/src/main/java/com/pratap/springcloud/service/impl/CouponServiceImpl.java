package com.pratap.springcloud.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.springcloud.entities.CouponEntity;
import com.pratap.springcloud.exceptions.CouponServiceException;
import com.pratap.springcloud.repositories.CouponRepository;
import com.pratap.springcloud.service.CouponService;
import com.pratap.springcloud.shared.dto.CouponDTO;

@Service
public class CouponServiceImpl implements CouponService {
	
	private ModelMapper modelMapper;
	
	@Autowired
	private CouponRepository couponRepository;

	@Override
	public CouponDTO create(CouponDTO couponDTO) {
		
		// check for valid couponDTO
		//valid(couponDTO)
		
		
		modelMapper = new ModelMapper();
		CouponEntity couponEntity = modelMapper.map(couponDTO, CouponEntity.class);
		CouponEntity savedCouponEntity = couponRepository.save(couponEntity);
		
		CouponDTO returnCouponDto = modelMapper.map(savedCouponEntity, CouponDTO.class);
		return returnCouponDto;
	}

	@Override
	public CouponDTO getCoupon(String code) {

		modelMapper = new ModelMapper();
		CouponEntity couponEntity = couponRepository.findByCode(code).orElseThrow(() -> new CouponServiceException("Coupon Not Found By Code : "+code));
		return modelMapper.map(couponEntity, CouponDTO.class);
	}

	@Override
	public List<CouponDTO> getCoupons() {

		List<CouponEntity> couponEntities = couponRepository.findAll();
		if(couponEntities.size() == 0) {
			throw new CouponServiceException("No Coupon available.");
		}
		
		modelMapper = new ModelMapper();
		
		return couponEntities.stream().map(couponEntity -> modelMapper.map(couponEntity, CouponDTO.class)).collect(Collectors.toList());
	}

}
