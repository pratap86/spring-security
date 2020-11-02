package com.pratap.springcloud.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.springcloud.entity.CouponEntity;
import com.pratap.springcloud.exceptions.CouponServiceException;
import com.pratap.springcloud.repository.CouponRepository;
import com.pratap.springcloud.service.CouponService;
import com.pratap.springcloud.shared.dto.CouponDTO;
import com.pratap.springcloud.shared.dto.CouponUpdateDTO;

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

	@Override
	public CouponDTO updateCoupon(String code, CouponUpdateDTO couponUpdateRequest) {
		
		CouponDTO couponResp = getCoupon(code);
		
		if(couponUpdateRequest.getDiscount() != null) {
			couponResp.setDiscount(couponUpdateRequest.getDiscount());
		}
		if(couponUpdateRequest.getExpDate() != null) {
			couponResp.setExpDate(couponUpdateRequest.getExpDate());
		}
		
		modelMapper = new ModelMapper();
		CouponEntity couponEntity = modelMapper.map(couponResp, CouponEntity.class);
		
		CouponEntity savedCoupon = couponRepository.updateCoupon(couponEntity.getDiscount(), couponEntity.getExpDate(), code);
		
		return modelMapper.map(savedCoupon, CouponDTO.class);
	}

}
