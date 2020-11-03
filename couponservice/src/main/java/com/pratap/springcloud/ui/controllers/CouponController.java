package com.pratap.springcloud.ui.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pratap.springcloud.service.CouponService;
import com.pratap.springcloud.shared.dto.CouponDTO;
import com.pratap.springcloud.ui.model.request.CouponRequestModel;
import com.pratap.springcloud.ui.model.response.CouponResponseModel;

@Controller
public class CouponController {
	
	@Autowired
	private CouponService couponService;

	private ModelMapper modelMapper;
	
	@GetMapping("/showCreateCoupon")
	public String createCoupon() {
		return "createCoupon";
	}
	
	@PostMapping("/saveCoupon")
	public String save(CouponRequestModel couponRequest) {
		
		modelMapper = new ModelMapper();
		CouponDTO couponDTOReq = modelMapper.map(couponRequest, CouponDTO.class);
		couponService.create(couponDTOReq);
		return "createResponse";
	}
	
	@GetMapping("/showGetCoupon")
	public String showGetCoupon() {
		return "getCoupon";
	}
	
	@PostMapping("/getCoupon")
	public ModelAndView getCoupon(String code) {
		ModelAndView mav = new ModelAndView("couponDetails");
		CouponDTO couponDTOResp = couponService.getCoupon(code);
		modelMapper = new ModelMapper();
		CouponResponseModel couponResponseModel = modelMapper.map(couponDTOResp, CouponResponseModel.class);
		mav.addObject(couponResponseModel);
		return mav;
	}
}
