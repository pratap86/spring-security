package com.pratap.springcloud.ui.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pratap.springcloud.service.ProductService;
import com.pratap.springcloud.shared.ProductDTO;
import com.pratap.springcloud.ui.model.request.ProductRequestModel;
import com.pratap.springcloud.ui.model.response.CouponResponseModel;
import com.pratap.springcloud.ui.model.response.ProductResponseModel;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
	
	@Autowired
	private ProductService productService;

	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${couponservice.url}")
	private String couponServiceURL;
	
	@PostMapping("/products")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProductResponseModel createProduct(@RequestBody ProductRequestModel productRequest) {
		
		logger.info("executing createProduct() ", productRequest);
		modelMapper = new ModelMapper();
		ProductDTO productDTO = modelMapper.map(productRequest, ProductDTO.class);
		
		logger.info("Before executing the couponservice, URL is "+couponServiceURL+productRequest.getCouponCode());
		CouponResponseModel couponResponseModel = restTemplate.getForObject(couponServiceURL+productRequest.getCouponCode(), CouponResponseModel.class);
		
		logger.info("After executing the couponservice, resp is "+couponResponseModel);
		productDTO.setPrice(productRequest.getPrice().subtract(couponResponseModel.getDiscount()));
		
		ProductDTO savedProductDTO = productService.create(productDTO);
		
		return modelMapper.map(savedProductDTO, ProductResponseModel.class);
	}
	
	@GetMapping("/products/{id}")
	public ProductResponseModel getProduct(@PathVariable("id") Long id) {
		
		ProductDTO productDTO = productService.getProductDetails(id);
		modelMapper = new ModelMapper();
		return modelMapper.map(productDTO, ProductResponseModel.class);
		
	}
}
