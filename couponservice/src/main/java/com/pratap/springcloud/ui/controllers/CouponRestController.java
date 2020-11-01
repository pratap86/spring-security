package com.pratap.springcloud.ui.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratap.springcloud.service.CouponService;
import com.pratap.springcloud.shared.dto.CouponDTO;
import com.pratap.springcloud.ui.model.request.CouponRequestModel;
import com.pratap.springcloud.ui.model.response.CouponResponseModel;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {
	
	private ModelMapper modelMapper;
	
	@Autowired
	private CouponService couponService;

	/**
	 * 
	 * @param CouponRequestModel as requestModel
	 * @return CouponResponseModel as couponResponseModel
	 */
	@PostMapping("/coupons")
	public ResponseEntity<CouponResponseModel> createCoupon(@RequestBody CouponRequestModel requestModel) {
		
		modelMapper = new ModelMapper();
		
		CouponDTO couponDTO = modelMapper.map(requestModel, CouponDTO.class);
		CouponDTO returnCouponDTO = couponService.create(couponDTO);
		
		CouponResponseModel couponResponseValue = modelMapper.map(returnCouponDTO, CouponResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(couponResponseValue);
	}
	
	@GetMapping("/coupons/{code}")
	public ResponseEntity<CouponResponseModel> getCoupon(@PathVariable("code") String code) {
		modelMapper = new ModelMapper();
		CouponDTO couponDTO = couponService.getCoupon(code);
		
		CouponResponseModel responseValue = modelMapper.map(couponDTO, CouponResponseModel.class);
		return ResponseEntity.status(HttpStatus.FOUND).body(responseValue);
	}
	
	@GetMapping("/coupons")
	public ResponseEntity<List<CouponResponseModel>> getCoupons(){
		
		List<CouponDTO> couponsdtos = couponService.getCoupons();
		
		modelMapper = new ModelMapper();
		
		List<CouponResponseModel> couponsResp = couponsdtos.stream().map(dto -> modelMapper.map(dto, CouponResponseModel.class)).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(couponsResp); 
	}
}
