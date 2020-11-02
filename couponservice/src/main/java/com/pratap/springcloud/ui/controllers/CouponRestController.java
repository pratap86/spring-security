package com.pratap.springcloud.ui.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(value = HttpStatus.CREATED)
	public CouponResponseModel createCoupon(@Valid @RequestBody CouponRequestModel requestModel) {
		
		modelMapper = new ModelMapper();
		
		CouponDTO couponDTO = modelMapper.map(requestModel, CouponDTO.class);
		CouponDTO returnCouponDTO = couponService.create(couponDTO);
		
		return modelMapper.map(returnCouponDTO, CouponResponseModel.class);
	}
	
	@GetMapping("/coupons/{code}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public CouponResponseModel getCoupon(@PathVariable("code") String code) {
		CouponDTO couponDTO = couponService.getCoupon(code);
		modelMapper = new ModelMapper();
		return modelMapper.map(couponDTO, CouponResponseModel.class);
	}
	
	@GetMapping("/coupons")
	@ResponseStatus(code = HttpStatus.OK)
	public List<CouponResponseModel> getCoupons(){
		List<CouponDTO> couponsdtos = couponService.getCoupons();
		modelMapper = new ModelMapper();
		return couponsdtos.stream().map(dto -> modelMapper.map(dto, CouponResponseModel.class)).collect(Collectors.toList()); 
	}
}
